package ru.javalab.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.javalab.models.Troupe;
import ru.javalab.models.QTroupe;

public interface TroupeRepository extends PagingAndSortingRepository<Troupe, Long>, QuerydslPredicateExecutor<Troupe>, QuerydslBinderCustomizer<QTroupe> {
    @Override
    default void customize(QuerydslBindings bindings, QTroupe qTroupe) {
        bindings.bind(qTroupe.histrionics.any().name).as("histrionics.name").first(
                StringExpression::containsIgnoreCase
        );
    }
}

