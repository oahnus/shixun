package top.oahnus.service;

import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.CourseSelection;

import java.util.List;

/**
 * Created by oahnus on 2017/4/8
 * 21:22.
 */
public interface CourseSelectionService {
    Page<List<CourseSelection>> selectCourseSelectionByCourseId(String courseId, Integer page, Integer limit);
    Page<List<CourseSelection>> selectCourseSelectionByStudentId(String studentId, Integer page, Integer limit);

    CourseSelection insertNewCourseSelection(CourseSelectionDto courseSelectionDto);
}
