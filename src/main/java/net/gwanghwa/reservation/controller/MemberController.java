package net.gwanghwa.reservation.controller;

import net.gwanghwa.reservation.service.MemberService;
import net.gwanghwa.reservation.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @packageName   : net.gwanghwa.reservation.controller
* @fileName      : MemberController.java
* @author        : GwangHwa Lee
* @date          : 2024.11.08
* @description   : 회원 관리와 관련된 API를 처리하는 컨트롤러
*/
@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
    * 회원 가입을 처리하는 API
    * @param name       사용자 이름
    * @param email      사용자 이메일
    * @param password   사용자 비밀번호
    * @param role       사용자 역할 (일반 사용자: ROLE_USER, 점장: ROLE_PARTNER)
    * @return 생성된 회원 객체
    */
    @PostMapping("/register")
    public Member registerMember(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
        return memberService.registerMember(name, email, password, role);
    }
}