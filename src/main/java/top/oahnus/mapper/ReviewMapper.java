package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Review;

import java.util.List;

/**
 * Created by oahnus on 2017/4/26
 * 13:56.
 */
@Mapper
public interface ReviewMapper extends BaseMapper {
    List<Review> selectReviewByParam(@Param("courseId")String courseId,
                                     @Param("username")String username,
                                     @Param("offset")Integer offset,
                                     @Param("limit")Integer limit);

    Review selectReviewById(@Param("id")String id);
    Integer deleteReviewById(@Param("id")String id);
    Integer insertNewReview(Review review);
}
