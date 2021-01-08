package ru.javalab.repositories.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import ru.javalab.dto.TroupeDto;
import ru.javalab.dto.TroupeRequest;
import ru.javalab.models.Troupe;
import ru.javalab.repositories.TroupesByRequestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javalab.models.QTroupe.troupe;

@Repository
public class TroupesByRequestRepositoryImpl implements TroupesByRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TroupeDto> findByRequest(TroupeRequest troupeRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (troupeRequest.getName() != null) {
            predicate.or(troupe.name.containsIgnoreCase(troupeRequest.getName()));
        }
        if (troupeRequest.getState() != null) {
            predicate.or(troupe.state.containsIgnoreCase(troupeRequest.getState()));
        }

        return new JPAQuery<Troupe>(entityManager)
                .select(troupe.name, troupe.state)
                .from(troupe)
                .where(predicate)
                .fetch().stream()
                .map(row -> TroupeDto.builder()
                        .name(row.get(troupe.name))
                        .state(row.get(troupe.state))
                        .build())
                .collect(Collectors.toList());
    }
}
