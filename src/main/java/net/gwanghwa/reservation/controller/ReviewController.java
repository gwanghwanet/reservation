package net.gwanghwa.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.gwanghwa.reservation.entity.Review;
import net.gwanghwa.reservation.service.ReviewService;

/**
* @packageName   : net.gwanghwa.reservation.controller
* @fileName      : ReviewController.java
* @author        : GwangHwa Lee
* @date          : 2024.11.17
* @description   : 리뷰 관리와 관련된 API를 처리하는 컨트롤러
*/
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 리뷰 작성 API
     * @param reservationId 예약 ID (리뷰 작성은 예약 완료 후 가능)
     * @param content 리뷰 내용
     * @param rating 리뷰 평점 (1~5)
     * @return 작성된 리뷰 객체
     */
    @PostMapping("/write")
    public Review writeReview(@RequestParam(value="reservationId") Long reservationId,
                              @RequestParam(value="content") String content,
                              @RequestParam(value="rating") int rating) {
        return reviewService.writeReview(reservationId, content, rating);
    }

    /**
     * 특정 예약에 대한 리뷰 조회 API
     * @param reservationId 예약 ID
     * @return 리뷰 목록
     */
    @GetMapping("/{reservationId}")
    public Review getReviewByReservation(@PathVariable Long reservationId) {
        return reviewService.getReviewByReservation(reservationId);
    }

    /**
     * 리뷰 수정 API
     * @param reviewId 리뷰 ID
     * @param content 새로운 리뷰 내용
     * @param rating 새로운 평점
     * @return 수정된 리뷰 객체
     */
    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId,
            @RequestParam(value="content") String content,
            @RequestParam(value="rating") int rating) {
        return reviewService.updateReview(reviewId, content, rating);
    }

    /**
     * 리뷰 삭제 API
     * @param reviewId 리뷰 ID
     */
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}