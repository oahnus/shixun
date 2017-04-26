package top.oahnus.service;

import top.oahnus.dto.Page;
import top.oahnus.dto.ReviewDto;
import top.oahnus.entity.Review;

import java.util.List;

/**
 * Created by oahnus on 2017/4/26
 * 14:04.
 */
public interface ReviewService {
    Page<List<Review>> selectReviewByParam(String courseId,String username,Integer page,Integer limit);
    Review selectReviewById(String reviewId);
    Review insertNewReview(ReviewDto reviewDto);
    Integer deleteReview(String reviewId);
}
