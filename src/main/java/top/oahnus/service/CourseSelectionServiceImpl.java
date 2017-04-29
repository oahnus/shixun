package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;
import top.oahnus.entity.CourseSelection;
import top.oahnus.entity.Score;
import top.oahnus.entity.Student;
import top.oahnus.enums.CourseState;
import top.oahnus.exception.*;
import top.oahnus.mapper.CourseMapper;
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
    private CourseMapper courseMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public Page<List<CourseSelection>> selectCourseSelectionByCourseId(String courseId, CourseState courseState, Integer page, Integer limit) {
        if (StringUtil.isEmpty(courseId) || page == null || limit == null)
            throw new BadRequestParamException("请求参数错误");
        if (courseState == null) {
            courseState = CourseState.INIT;
        }
        CourseState finalCourseState = courseState;
        System.out.println(courseState.ordinal());
        List<CourseSelection> courseSelections = courseSelectionMapper.selectCourseSelectionByCourseId(courseId, courseState.ordinal(),(page - 1)*limit, limit);
        System.out.println(courseSelections.size());
        Integer totalRecord = courseSelectionMapper.selectRecordCount(
                new HashMap<String, String>(){{
                    put("courseId", courseId);
                    put("courseState", String.valueOf(finalCourseState.ordinal()));
                }});
        return new Page<>(courseSelections, totalRecord, page, limit);
    }

    @Override
    public Page<List<CourseSelection>> selectCourseSelectionByStudentId(String studentId, CourseState courseState, Integer page, Integer limit) {
        if (page == null || limit == null || StringUtil.isEmpty(studentId))
            throw new BadRequestParamException("请求参数错误");
        if (courseState == null) {
            courseState = CourseState.INIT;
        }
        CourseState finalCourseState = courseState;

        List<CourseSelection> courseSelections = courseSelectionMapper.selectCourseSelectionByStudentId(studentId, courseState.ordinal(),(page - 1)*limit, limit);
        Integer totalRecord = courseSelectionMapper.selectRecordCount(
                new HashMap<String, String>(){{
                    put("studentId", studentId);
                    put("courseState", String.valueOf(finalCourseState.ordinal()));
                }});
        return new Page<>(courseSelections, totalRecord, page, limit);
    }


    @Override
    @Transactional
    public CourseSelection insertNewCourseSelection(CourseSelectionDto courseSelectionDto) {
        CourseSelection courseSelection = new CourseSelection(courseSelectionDto);

        //查询课程状态
        Course course = courseMapper.selectCourseById(courseSelectionDto.getCourseId());

        CourseState state = course.getState();
        if (!state.equals(CourseState.ON_SELECTED)) {
            throw new DataStatusException("课程还未开放选课");
        }

        // todo 判断选课信息是否在课程可选专业范围内
        // 插入选课信息
        Integer count = courseSelectionMapper.insertNewCourseSelection(courseSelection);
        if (count == 0) {
            throw new DataExistedException("数据已存在");
        } else if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else {
            CourseSelection cs = courseSelectionMapper.selectCourseSelectionByStudentIdAndCourseId(
                    courseSelection.getStudent().getId(),
                    courseSelection.getCourse().getId()
            );

            Score score = new Score();
            score.setCompanyScore(0F);
            score.setTeacherScore(0F);
            score.setCourseSelection(cs);
            score.setStudent(courseSelection.getStudent());
            scoreMapper.insertNewScore(score);
            return cs;
        }
    }

    @Override
    @Transactional
    public Integer deleteCourseSelectionById(String courseSelectionId) {
        if (StringUtil.isEmpty(courseSelectionId)) throw new BadRequestParamException("请求参数错误");
        Integer count = courseSelectionMapper.deleteCourseSelectionById(courseSelectionId);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("删除操作失败");
        } else if (count == 0) {
            throw new NotFoundException("数据未找到");
        }
        scoreMapper.deleteScoreByCourseSelectionId(courseSelectionId);
        return count;
    }
}
