package net.gwanghwa.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gwanghwa.reservation.entity.Review;

/**
* @packageName   : net.gwanghwa.reservation.repository
* @fileName      : ReviewRepository.java
* @author        : GwangHwa Lee
* @date          : 2024.11.17
* @description   : 리뷰 정보를 처리하는 리포지토리
*/
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
    * 특정 예약에 대한 리뷰를 조회
    * @param reservationId  예약 ID
    * @return 리뷰 객체
    */
    Optional<Review> findByReservationId(Long reservationId);

    
    /**
    * 특정 리뷰를 삭제
    * @param reviewId   리뷰 ID
    */
    void deleteById(Long reviewId);
}