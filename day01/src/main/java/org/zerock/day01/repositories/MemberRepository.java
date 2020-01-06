package org.zerock.day01.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.zerock.day01.domain.Member;

/**
 * MemberRepository
 */
public interface MemberRepository extends PagingAndSortingRepository<Member,String> {

    
}