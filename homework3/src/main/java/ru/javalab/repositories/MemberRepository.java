package ru.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.models.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
