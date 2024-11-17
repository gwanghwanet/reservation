package net.gwanghwa.reservation.controller;

import net.gwanghwa.reservation.entity.Member;
import net.gwanghwa.reservation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

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
    public ResponseEntity<Member> registerMember(@RequestParam(value="name") String name,
                                                 @RequestParam(value="email") String email,
                                                 @RequestParam(value="password") String password,
                                                 @RequestParam(value="role") String role) {
    // 역할은 ROLE_USER 또는 ROLE_PARTNER 중 하나로 설정
    if (!role.equals("ROLE_USER") && !role.equals("ROLE_PARTNER")) {
        return ResponseEntity.badRequest().body(null);
    }
    
    Member registeredMember = memberService.registerMember(name, email, password, role);
        return ResponseEntity.ok(registeredMember);
    }
    
    /**
     * 로그인 처리 API
     * @param email      사용자 이메일
     * @param password   사용자 비밀번호
     * @param session    HTTP 세션
     * @return 로그인 결과
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestParam(value="email") String email,
                                              @RequestParam(value="password") String password,
                                              HttpSession session) {
        // 이메일로 사용자 조회
        Member member = memberService.login(email, password);

        if (member == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // 로그인 성공 후 세션에 사용자 정보 저장
        session.setAttribute("loggedInMember", member);

        return ResponseEntity.ok("로그인 성공");
    }

    /**
     * 로그아웃 처리 API
     * @param session    HTTP 세션
     * @return 로그아웃 결과
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logoutMember(HttpSession session) {
     // 세션에서 사용자 정보 제거
        session.invalidate(); // 세션을 무효화하여 로그아웃 처리
        return ResponseEntity.ok("로그아웃 성공");
    }

    /**
     * 로그인된 사용자 정보 가져오기 API
     * @param session    HTTP 세션
     * @return 로그인된 사용자 정보
     */
    @GetMapping("/profile")
    public ResponseEntity<Member> getProfile(HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");

        if (loggedInMember == null) {
            return ResponseEntity.status(401).body(null);  // 로그인되지 않은 경우
        }

        return ResponseEntity.ok(loggedInMember);  // 로그인된 사용자 정보 반환
    }
}