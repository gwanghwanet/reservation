package net.gwanghwa.reservation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gwanghwa.reservation.entity.Reservation;

/**
* @packageName   : net.gwanghwa.reservation.repository
* @fileName      : ReservationRepository.java
* @author        : GwangHwa Lee
* @date          : 2024.11.15
* @description   : 예약 정보를 처리하는 리포지토리
*/
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 사용자가 예약한 모든 예약 목록을 조회
    List<Reservation> findByMemberId(Long memberId);

    // 특정 매장에서 예약 시간에 이미 예약이 존재하는지 확인
    boolean existsByStoreIdAndReservationTime(Long storeId, LocalDateTime reservationTime);
}
