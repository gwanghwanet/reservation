package net.gwanghwa.reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.gwanghwa.reservation.type.Role;

/**
* @packageName : net.gwanghwa.reservation.vo
* @fileName	: UserVO.java
* @author 		: GwangHwa Lee
* @date 		: 2024.11.08
* @description : 회원 정보를 다루는 엔티티
*/
@Entity
public class Member {

    // 기본 키 (회원을 유일하게 구분할 수 있는 ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 회원 이름
     */
    private String name;

    /**
     * 회원 이메일 (유니크)
     */
    private String email;

    /**
     * 회원 비밀번호
     */
    private String password;

    /**
     * 회원 역할 (ROLE_USER, ROLE_PARTNER)
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
