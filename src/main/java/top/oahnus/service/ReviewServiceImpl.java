package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.Page;
import top.oahnus.dto.ReviewDto;
import top.oahnus.entity.Review;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.ReviewMapper;
import top.oahnus.util.StringUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oahnus on 2017/4/26
 * 14:07.
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Page<List<Review>> selectReviewByParam(String courseId, String username, Integer page, Integer limit) {
        if (page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<Review> reviews = reviewMapper.selectReviewByParam(courseId, username, (page-1)*limit, limit);
        Integer totalRecord = reviewMapper.selectRecordCount(new HashMap<String, String>(){{
            put("courseId", courseId);
            put("username", username);
        }});
        return new Page<>(reviews, totalRecord, page, limit);
    }

    @Override
    public Review selectReviewById(String reviewId) {
        if (StringUtil.isEmpty(reviewId)) {
            throw new BadRequestParamException("评论id不能为空");
        }
        return reviewMapper.selectReviewById(reviewId);
    }

    @Override
    public Review insertNewReview(ReviewDto reviewDto) {
        Review review = new Review(reviewDto);
        Integer count = reviewMapper.insertNewReview(review);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("创建评论失败");
        } else if (count == 0) {

        } else {

        }
        return null;
    }

    @Override
    public Integer deleteReview(String reviewId) {
        if (StringUtil.isEmpty(reviewId)) {
            throw new BadRequestParamException("评论id不能为空");
        }
        return reviewMapper.deleteReviewById(reviewId);
    }
}
