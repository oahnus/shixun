package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.Score;

import java.util.List;

/**
 * Created by oahnus on 2017/4/19
 * 17:08.
 */
@Mapper
public interface ScoreMapper extends BaseMapper{
    List<Score> selectScoreByStudentId(@Param("studentId")String studentId);
    List<Score> selectScoreByCourseId(@Param("courseId")String courseId);

    Integer updateScoreByTeacher(Score score);
    Integer updateScoreByCompany(Score score);
}
