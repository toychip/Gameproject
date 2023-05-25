package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository <Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String paassword);

    Optional<Member> findByEmail(String email);

}
