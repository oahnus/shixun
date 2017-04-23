package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.Course;
import top.oahnus.entity.CourseSelection;
import top.oahnus.enums.CourseState;
import top.oahnus.exception.*;
import top.oahnus.mapper.CourseMapper;
import top.oahnus.mapper.CourseSelectionMapper;
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


    // todo 插入选课表之前，先判断课程状态是否开放选课，之后判断学生专业是否在课程可选择专业范围内
    @Override
    @Transactional
    public CourseSelection insertNewCourseSelection(CourseSelectionDto courseSelectionDto) {
        CourseSelection courseSelection = new CourseSelection(courseSelectionDto);

        //查询课程状态
        // todo 从redis中读取课程信息
        Course course = courseMapper.selectCourseById(courseSelectionDto.getCourseId());

        CourseState state = course.getState();
        if (!state.equals(CourseState.ON_SELECTED)) {
            throw new DataStatusException("课程还未开放选课");
        }

        // 插入选课信息
        Integer count = courseSelectionMapper.insertNewCourseSelection(courseSelection);
        if (count == 0) {
            throw new DataExistedException("数据已存在");
        } else if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else {
            // 创建选课信息的同时,创建分数表数据
            CourseSelection cs = courseSelectionMapper.selectCourseSelectionByStudentIdAndCourseId(
                    courseSelection.getStudent().getId(),
                    courseSelection.getCourse().getId()
            );
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
            throw new NotFoundException("数据为找到");
        }
        return count;
    }
}
