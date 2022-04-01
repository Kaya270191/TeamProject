package com.WPsports.repository;

import com.WPsports.dto.MemberForm;
import com.WPsports.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member getById(String id);

    @Query(value = "select member_key, id, pw, name, email, address, phone, birthday,auth from member", nativeQuery = true)
    List<Member> selectAllSQL();

}
