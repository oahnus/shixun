package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.CourseSelection;
import top.oahnus.exception.BadRequestParamException;
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

    @Override
    public Page<List<CourseSelection>> selectCourseSelectionByCourseId(String courseId, Integer page, Integer limit) {
        if (page == null || limit == null || StringUtil.isEmpty(courseId))
            throw new BadRequestParamException("请求参数错误");
        List<CourseSelection> courseSelections = courseSelectionMapper.selectCourseSelectionByCourseId(courseId, page, limit);
        Integer totalRecord = courseSelectionMapper.selectRecordCount(new HashMap<String, String>(){{put("courseId", courseId);}});
        return new Page<>(courseSelections, totalRecord, page, limit);
    }

    @Override
    public Page<List<CourseSelection>> selectCourseSelectionByStudentId(String studentId, Integer page, Integer limit) {
        if (page == null || limit == null || StringUtil.isEmpty(studentId))
            throw new BadRequestParamException("请求参数错误");
        List<CourseSelection> courseSelections = courseSelectionMapper.selectCourseSelectionByStudentId(studentId, page, limit);
        Integer totalRecord = courseSelectionMapper.selectRecordCount(new HashMap<String, String>(){{put("studentId", studentId);}});
        return new Page<>(courseSelections, totalRecord, page, limit);
    }

    @Override
    public CourseSelection insertNewCourseSelection(CourseSelectionDto courseSelectionDto) {
        return null;
    }
}
