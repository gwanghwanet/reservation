package net.gwanghwa.reservation.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.gwanghwa.reservation.entity.Reservation;
import net.gwanghwa.reservation.service.ReservationService;

/**
* @packageName   : net.gwanghwa.reservation.controller
* @fileName      : ReservationController.java
* @author        : GwangHwa Lee
* @date          : 2024.11.15
* @description   : 예약 관리와 관련된 API를 처리하는 컨트롤러
*/
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * 예약을 생성하는 API
     * @param reservation 예약 객체
     * @return 생성된 예약 객체
     */
    @PostMapping("/create")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    /**
     * 예약을 확정하는 API
     * @param reservationId 예약 ID
     * @return 확정된 예약 객체
     */
    public Reservation confirmReservation(@PathVariable Long reservationId) {
        return reservationService.confirmReservation(reservationId);
    }

    /**
     * 예약을 취소하는 API
     * @param reservationId 예약 ID
     * @return 취소된 예약 객체
     */
    @PutMapping("/cancel/{reservationId}")
    public Reservation cancelReservation(@PathVariable Long reservationId) {
        return reservationService.cancelReservation(reservationId);
    }

    /**
     * 예약을 완료하는 API
     * @param reservationId 예약 ID
     * @return 완료된 예약 객체
     */
    @PutMapping("/complete/{reservationId}")
    public Reservation completeReservation(@PathVariable Long reservationId) {
        return reservationService.completeReservation(reservationId);
    }

    /**
     * 도착 확인을 진행하는 API
     * @param reservationId 예약 ID
     * @param currentTime 도착 확인을 요청하는 시간
     * @return 도착 확인 후 상태가 업데이트된 예약 객체
     */
    @PutMapping("/checkin/{reservationId}")
    public Reservation checkInReservation(@PathVariable Long reservationId, @RequestParam("currentTime") String currentTime) {
        // String을 LocalDateTime으로 변환
        LocalDateTime checkInTime = LocalDateTime.parse(currentTime);
        return reservationService.checkInReservation(reservationId, checkInTime);
    }
}