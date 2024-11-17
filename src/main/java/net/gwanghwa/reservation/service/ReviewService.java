package net.gwanghwa.reservation.service;

import net.gwanghwa.reservation.entity.Review;
import net.gwanghwa.reservation.entity.Reservation;
import net.gwanghwa.reservation.repository.ReservationRepository;
import net.gwanghwa.reservation.repository.ReviewRepository;
import net.gwanghwa.reservation.type.ReservationStatus;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @packageName   : net.gwanghwa.reservation.service
* @fileName      : ReviewService.java
* @author        : GwangHwa Lee
* @date          : 2024.11.17
* @description   : 리뷰 관련 서비스
*/
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
    * 리뷰 작성 처리
    * @param reservationId 예약 ID
    * @param content 리뷰 내용
    * @param rating 리뷰 평점
    * @return 작성된 리뷰 객체
    */
    public Review writeReview(Long reservationId, String content, int rating) {
        // 예약 찾기
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다."));

        // 예약 상태 확인 (예약 완료 후 리뷰 작성 가능)
        if (reservation.getStatus() == ReservationStatus.COMPLETED) {
            throw new IllegalArgumentException("예약이 완료되지 않았습니다. 도착 확인 후 리뷰를 작성할 수 있습니다.");
        }

        // 리뷰 작성
        Review review = new Review();
        review.setContent(content);
        review.setRating(rating);
        review.setReservation(reservation);
        review.setCreatedAt(LocalDateTime.now());

        // 리뷰 저장
        return reviewRepository.save(review);
    }

    /**
    * 특정 예약에 대한 리뷰 조회
    * @param reservationId 예약 ID
    * @return 리뷰 객체
    */
    public Review getReviewByReservation(Long reservationId) {
        return reviewRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약에 대한 리뷰가 없습니다."));
    }

    /**
    * 리뷰 수정
    * @param reviewId 리뷰 ID
    * @param content 수정할 내용
    * @param rating 수정할 평점
    * @return 수정된 리뷰 객체
    */
    public Review updateReview(Long reviewId, String content, int rating) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));
        
        review.setContent(content);
        review.setRating(rating);
        review.setUpdatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    /**
     * 리뷰 삭제
     * @param reviewId 리뷰 ID
     */
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        reviewRepository.delete(review);
    }
}