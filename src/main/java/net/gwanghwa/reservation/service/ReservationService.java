package net.gwanghwa.reservation.service;

import net.gwanghwa.reservation.entity.Reservation;
import net.gwanghwa.reservation.repository.ReservationRepository;
import net.gwanghwa.reservation.type.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @packageName   : net.gwanghwa.reservation.service
* @fileName      : ReservationService.java
* @author        : GwangHwa Lee
* @date          : 2024.11.15
* @description   : 예약 관련 서비스
*/
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * 예약 상태를 'CONFIRMED'로 변경
     * @param reservationId 예약 ID
     * @return 예약 객체
     */
    public Reservation confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다."));
        
        // 예약 상태를 'CONFIRMED'로 변경
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.setUpdatedAt(LocalDateTime.now());
        
        return reservationRepository.save(reservation);
    }

    /**
     * 예약 상태를 'CANCELLED'로 변경
     * @param reservationId 예약 ID
     * @return 예약 객체
     */
    public Reservation cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다."));
        
        // 예약 상태를 'CANCELLED'로 변경
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.setUpdatedAt(LocalDateTime.now());
        
        return reservationRepository.save(reservation);
    }

    /**
     * 예약 상태를 'COMPLETED'로 변경
     * @param reservationId 예약 ID
     * @return 예약 객체
     */
    public Reservation completeReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다."));
        
        // 예약 상태를 'COMPLETED'로 변경
        reservation.setStatus(ReservationStatus.COMPLETED);
        reservation.setUpdatedAt(LocalDateTime.now());
        
        return reservationRepository.save(reservation);
    }

    /**
     * 예약을 생성하고 상태를 'PENDING'으로 설정
     * @param reservation 예약 객체
     * @return 생성된 예약 객체
     */
    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.PENDING);  // 기본 상태는 'PENDING'
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());
        
        return reservationRepository.save(reservation);
    }

    /**
     * 예약 도착 확인
     * 예약 시간이 현재 시간 기준으로 10분 전에 도착해야만 도착 확인이 가능
     * @param reservationId 예약 ID
     * @param currentTime 현재 시간 (도착 확인을 요청하는 시간)
     * @return 예약 객체
     */
    public Reservation checkInReservation(Long reservationId, LocalDateTime currentTime) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다."));

        // 예약 상태가 CONFIRMED이어야만 도착 확인 가능
        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("예약이 확정되지 않았습니다. 도착 확인을 할 수 없습니다.");
        }

        // 예약 시간보다 10분 전에 도착해야만 도착 확인 가능
        LocalDateTime reservationTime = reservation.getReservationTime();
        LocalDateTime checkInTimeWindow = reservationTime.minusMinutes(10);

        if (currentTime.isBefore(checkInTimeWindow)) {
            throw new IllegalStateException("예약 시간이 아니므로 도착 확인을 할 수 없습니다.");
        }

        // 예약 시간이 10분 이후일 경우, 도착 확인 불가능
        if (currentTime.isAfter(reservationTime)) {
            throw new IllegalStateException("예약 시간이 지나서 도착 확인을 할 수 없습니다.");
        }

        // 도착 확인이 가능하면 상태를 업데이트
        reservation.setStatus(ReservationStatus.COMPLETED);  // 도착 확인 후 예약 완료 처리
        reservation.setUpdatedAt(currentTime);

        return reservationRepository.save(reservation);
    }
}