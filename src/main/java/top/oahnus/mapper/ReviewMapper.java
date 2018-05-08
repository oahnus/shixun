package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Review;

import java.util.List;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/26
 * 13:56.
 */
@Mapper
@Repository
public interface ReviewMapper extends BaseMapper {
    List<Review> selectReviewByParam(@Param("courseId") String courseId,
                                     @Param("fromUsername") String fromUsername,
                                     @Param("toUsername") String toUsername,
                                     @Param("rate") Integer rate,
                                     @Param("offset")Integer page,
                                     @Param("limit")Integer limit);

    Review selectReviewById(@Param("id")String id);
    Integer deleteReviewById(@Param("id")String id);
    Integer insertNewReview(Review review);
}
