package net.gwanghwa.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gwanghwa.reservation.entity.Member;

/**
* @packageName   : net.gwanghwa.reservation.repository
* @fileName      : MemberRepository.java
* @author        : GwangHwa Lee
* @date          : 2024.11.08
* @description   : 회원 정보를 처리하는 리포지토리
*/
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
}
