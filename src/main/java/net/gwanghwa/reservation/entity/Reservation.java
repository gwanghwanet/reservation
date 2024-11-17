package net.gwanghwa.reservation.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import net.gwanghwa.reservation.type.ReservationStatus;

/**
* @packageName   : net.gwanghwa.reservation.vo
* @fileName      : ReservationVO.java
* @author        : GwangHwa Lee
* @date          : 2024.11.15
* @description   : 예약 정보를 다루는 엔티티
*/
@Entity
public class Reservation {

    /**
     * 예약의 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 예약된 매장 정보
     */
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * 예약한 회원 정보
     */
    @ManyToAny
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * 예약 시간
     */
    private LocalDateTime reservationTime; // 예약 시간

    /**
     * 예약 상태 (PENDING, CONFIRMED, COMPLETED, CANCELLED)
     */
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    
    /**
     * 예약 생성 일시
     */
    private LocalDateTime createdAt;
    
    /**
     * 예약 수정 일시
     */
    private LocalDateTime updatedAt;

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
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * @return the reservationTime
     */
    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    /**
     * @param reservationTime the reservationTime to set
     */
    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * @return the status
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    /**
     * @return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
