package net.gwanghwa.reservation.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

/**
* @packageName   : net.gwanghwa.reservation.vo
* @fileName      : StoreVO.java
* @author        : GwangHwa Lee
* @date          : 2024.11.13
* @description   : 매장 정보 엔티티
*/
@Entity
public class Store {

    /**
     * 매장 고유 ID
     */    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 매장 이름
     */
    private String name;

    /**
     * 매장 위치
     */
    private String location;

    /**
     * 매장 설명
     */
    private String description;

    /**
     * 매장 소유자 (점장)
     */
    @ManyToAny //
    @JoinColumn(name = "owner_id")
    private Member owner;

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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the owner
     */
    public Member getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Member owner) {
        this.owner = owner;
    }

}