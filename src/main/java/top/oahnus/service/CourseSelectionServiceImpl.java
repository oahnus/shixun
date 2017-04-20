package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.CourseSelection;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.mapper.CourseSelectionMapper;
import top.oahnus.mapper.ScoreMapper;
import top.oahnus.util.StringUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oahnus on 2017/4/8
 * 21:23.
 */
@Service
public class CourseSelectionServiceImpl implements CourseSelectionService{
    @Autowired
    private CourseSelectionMapper courseSelectionMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public Page<List<CourseSelection>> selectCourseSelectionByCourseId(String courseId, Integer page, Integer limit) {
        if (StringUtil.isEmpty(courseId) || page == null || limit == null)
            throw new BadRequestParamException("请求参数错误");
        List<CourseSelection> courseSelections = courseSelectionMapper.selectCourseSelectionByCourseId(courseId, (page - 1)*limit, limit);
        Integer totalRecord = courseSelectionMapper.selectRecordCount(new HashMap<String, String>(){{put("courseId", courseId);}});
        return new Page<>(courseSelections, totalRecord, page, limit);
    }

    @Override
    public Page<List<CourseSelection>> selectCourseSelectionByStudentId(String studentId, Integer page, Integer limit) {
        if (page == null || limit == null || StringUtil.isEmpty(studentId))
            throw new BadRequestParamException("请求参数错误");
        List<CourseSelection> courseSelections = courseSelectionMapper.selectCourseSelectionByStudentId(studentId, (page - 1)*limit, limit);
        Integer totalRecord = courseSelectionMapper.selectRecordCount(new HashMap<String, String>(){{put("studentId", studentId);}});
        return new Page<>(courseSelections, totalRecord, page, limit);
    }

    @Override
    @Transactional
    public CourseSelection insertNewCourseSelection(CourseSelectionDto courseSelectionDto) {
        CourseSelection courseSelection = new CourseSelection(courseSelectionDto);
        Integer count = courseSelectionMapper.insertNewCourseSelection(courseSelection);
        if (count == 0) {
            throw new DataExistedException("数据已存在");
        } else if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else {
            // 创建选课信息的同时,创建分数表数据
            CourseSelection cs = courseSelectionMapper.selectCourseSelectionByStudentIdAndCourseIdAndCourseUpdateTime(
                    courseSelection.getStudent().getId(),
                    courseSelection.getCourse().getId(),
                    courseSelection.getCourseUpdateTime()
            );
            Integer c = scoreMapper.insertNewScore(courseSelection.getStudent().getId(),cs.getId());
            if (c < 0) {
                throw new SQLExecuteFailedExceeption("插入数据失败");
            } else {
                return cs;
            }
        }
    }
}
