package com.WPsports.repository;

import com.WPsports.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member getById(String id);
}
