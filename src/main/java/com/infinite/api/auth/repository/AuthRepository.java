package com.infinite.api.auth.repository;

import com.infinite.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Member, Long> {

}
