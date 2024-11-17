package net.gwanghwa.reservation.type;

/**
* @packageName   : net.gwanghwa.reservation.type
* @fileName      : ReservationStatus.java
* @author        : GwangHwa Lee
* @date          : 2024.11.15
* @description   : 예약 상태를 정의한 Enum
*/
public enum ReservationStatus {
    PENDING,    // 예약 대기 상태
    CONFIRMED,  // 예약 확정 상태
    COMPLETED,  // 예약 완료 상태
    CANCELLED   // 예약 취소 상태
}
