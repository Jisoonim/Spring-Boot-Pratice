package org.zerock.day6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.day6.domain.Member;

/**
 * MemberRepository
 */
public interface MemberRepository extends JpaRepository<Member, String> {


    @Modifying
    @Query("UPDATE FROM Member m SET m.nickname = :nickname WHERE m.mid = :mid")
    public void updateNickname(String mid, String nickname);
    
}