package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.Page;
import top.oahnus.dto.ScoreDto;
import top.oahnus.entity.Score;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.ScoreMapper;
import top.oahnus.util.StringUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oahnus on 2017/4/25
 * 14:14.
 */
@Service
public class ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    public Page<List<Score>> selectScoreByParam(String courseId, String studentId, Integer page, Integer limit) {
        if (page == null || limit == null) {
            throw new BadRequestParamException("请求参数错误");
        }
        if (!StringUtil.isEmpty(courseId) && !StringUtil.isEmpty(studentId)) {
            List<Score> scores = scoreMapper.selectScoreByStudentIdAndCourseId(studentId, courseId, (page-1)*limit, limit);
            Integer totalRecords = scoreMapper.selectRecordCount(new HashMap<String,String>(){{put("courseId", courseId);}});
            return new Page<>(scores, totalRecords, page, limit);
        }
        else if (!StringUtil.isEmpty(courseId)) {
            List<Score> scores = scoreMapper.selectScoreByCourseId(courseId, (page-1)*limit, limit);
            Integer totalRecords = scoreMapper.selectRecordCount(new HashMap<String,String>(){{put("courseId", courseId);}});
            return new Page<>(scores, totalRecords, page, limit);
        } else if (!StringUtil.isEmpty(studentId)) {
            List<Score> scores = scoreMapper.selectScoreByStudentId(studentId, (page-1)*limit, limit);
            Integer totalRecords = scoreMapper.selectRecordCount(new HashMap<String,String>(){{put("studentId", studentId);}});
            return new Page<>(scores, totalRecords, page, limit);
        } else {
            throw new BadRequestParamException("请求参数错误");
        }
    }

    public Integer updateScore(ScoreDto scoreDto) {
        AuthType type = scoreDto.getAuthType();
        Integer count;
        if (type.equals(AuthType.COMPANY)) {
            Score score = new Score(scoreDto);
            score.setCompanyScore(scoreDto.getScore());
            count = scoreMapper.updateCompanyScore(score);
        } else if (type.equals(AuthType.TEACHER)) {
            Score score = new Score(scoreDto);
            score.setTeacherScore(scoreDto.getScore());
            count = scoreMapper.updateTeacherScore(score);
        } else {
            throw new BadRequestParamException("非教师、公司角色无法打分");
        }
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else {
            return count;
        }
    }
}
