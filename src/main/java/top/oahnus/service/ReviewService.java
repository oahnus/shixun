package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import top.oahnus.dto.Page;
import top.oahnus.dto.ReviewDto;
import top.oahnus.entity.Review;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.ReviewMapper;
import top.oahnus.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/26
 * 14:07.
 */
@Service
public class ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    public Page<List<Review>> selectReviewByParam(String courseId,
                                                  String fromUsername,
                                                  String toUsername,
                                                  Integer rate,
                                                  Integer page,
                                                  Integer limit) {
        if (page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        Map<String, String> params = new HashMap<>();
        params.put("courseId", courseId);
        params.put("fromUsername", fromUsername);
        params.put("toUsername", toUsername);
        params.put("rate", String.valueOf(rate));
        List<Review> reviews = reviewMapper.selectReviewByParam(courseId, fromUsername, toUsername, rate, (page-1)*limit, limit);
        Integer totalRecord = reviewMapper.selectRecordCount(params);
        return new Page<>(reviews, totalRecord, page, limit);
    }

    public Review selectReviewById(String reviewId) {
        if (StringUtil.isEmpty(reviewId)) {
            throw new BadRequestParamException("评论id不能为空");
        }
        return reviewMapper.selectReviewById(reviewId);
    }

    public Integer insertNewReview(ReviewDto reviewDto) {
        Review review = new Review(reviewDto);
        Integer count = reviewMapper.insertNewReview(review);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("创建评论失败");
        } else if (count == 0) {
            throw new DataExistedException("已评论该课程");
        } else {
            reviewMapper.insertNewReview(review);
        }
        return count;
    }

    public Integer deleteReview(String reviewId) {
        if (StringUtil.isEmpty(reviewId)) {
            throw new BadRequestParamException("评论id不能为空");
        }
        return reviewMapper.deleteReviewById(reviewId);
    }
}
