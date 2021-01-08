package ru.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javalab.models.Troupe;
import ru.javalab.repositories.TroupeRepository;

@Service
public class TroupeServiceImpl implements TroupeService {

    @Autowired
    private TroupeRepository troupeRepository;

    @Override
    public Troupe publish(Long troupeId) {

        Troupe troupe = troupeRepository
                .findById(troupeId)
                .orElseThrow(() -> new IllegalArgumentException());

        troupe.publish();

        troupeRepository.save(troupe);

        return troupe;
    }
}
