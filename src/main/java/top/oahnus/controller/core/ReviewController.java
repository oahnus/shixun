package top.oahnus.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.dto.Page;
import top.oahnus.dto.ResponseData;
import top.oahnus.dto.ReviewDto;
import top.oahnus.entity.Review;
import top.oahnus.enums.ServerState;
import top.oahnus.service.ReviewService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/26
 * 14:23.
 */
@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseData<Page> selectReview(@RequestParam(value = "courseId", required = false)String courseId,
                                           @RequestParam(value = "fromUsername", required = false)String fromUsername,
                                           @RequestParam(value = "toUsername", required = false)String toUsername,
                                           @RequestParam(value = "rate", required = false)Integer rate,
                                           @RequestParam("page")Integer page,
                                           @RequestParam("limit")Integer limit){

        Page p = reviewService.selectReviewByParam(courseId, fromUsername, toUsername, rate, page, limit);
        return new ResponseData<>(ServerState.SUCCESS, p, "success");
    }

    @GetMapping("/{id}")
    public ResponseData<Review> selectReviewById(@PathVariable("id")String reviewId) {
        Review review = reviewService.selectReviewById(reviewId);
        return new ResponseData<>(ServerState.SUCCESS, review, "success");
    }

    @PostMapping
    public ResponseData<Integer> insertReview(@Validated @RequestBody ReviewDto reviewDto) {
        Integer count = reviewService.insertNewReview(reviewDto);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }

    @DeleteMapping("/{id}")
    public ResponseData<Integer> deleteByReviewId(@PathVariable("id")String reviewId) {
        Integer count = reviewService.deleteReview(reviewId);
        return new ResponseData<>(ServerState.SUCCESS, count, "success");
    }
}
