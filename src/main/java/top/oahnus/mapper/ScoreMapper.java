package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.Score;

import java.util.List;

/**
 * Created by oahnus on 2017/4/25
 * 13:35.
 */
@Mapper
@Repository
public interface ScoreMapper extends BaseMapper {
    List<Score> selectScoreByStudentId(@Param("studentId")String studentId, @Param("offset")Integer offset,@Param("limit")Integer limit);
    List<Score> selectScoreByCourseId(@Param("courseId")String courseId, @Param("offset")Integer offset,@Param("limit")Integer limit);
    List<Score> selectScoreByStudentIdAndCourseId(@Param("studentId")String studentId,
                                                  @Param("courseId")String courseId,
                                                  @Param("offset")Integer offset,
                                                  @Param("limit")Integer limit);
    Integer insertNewScore(Score score);

    Integer updateTeacherScore(Score score);
    Integer updateCompanyScore(Score score);

    Integer deleteScoreByCourseSelectionId(@Param("courseSelectionId")String courseSelectionId);
}
