package net.gwanghwa.reservation.service;

import net.gwanghwa.reservation.entity.Member;
import net.gwanghwa.reservation.repository.MemberRepository;
import net.gwanghwa.reservation.type.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @packageName : net.gwanghwa.reservation.service
 * @fileName : MemberService.java
 * @author : GwangHwa Lee
 * @date : 2024.11.08
 * @description : 회원 관련 서비스
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    
    /**
     * 회원 가입 처리
     * 
     * @param name     회원 이름
     * @param email    회원 이메일
     * @param password 회원 비밀번호
     * @param role     회원 역할
     * @return 저장된 회원 객체
     */
    public Member registerMember(String name, String email, String password, String role) {

        Role enumRole = null;
        if ("ROLE_USER".compareTo(role) == 0) {
            enumRole = Role.ROLE_USER;
        } else if ("ROLE_PARTNER".compareTo(role) == 0) {
            enumRole = Role.ROLE_PARTNER;
        }
        
        // 새로운 Member 객체 생성
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);
        member.setRole(enumRole);

        // DB에 저장하고 반환
        return memberRepository.save(member);
    }

    /**
    * 로그인 처리 
    * @param email      회원 이메일
    * @param password   회원 비밀번호
    * @return 저장된 회원 객체
    */
    public Member login(String email, String password) {
        // 이메일로 회원 찾기
        Member member = memberRepository.findByEmail(email);

        if (member != null && member.getPassword().equals(password)) {
            return member;
        }

        return member;
    }
}