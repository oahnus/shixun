package top.oahnus.service;

import top.oahnus.dto.CourseSelectionDto;
import top.oahnus.dto.Page;
import top.oahnus.entity.CourseSelection;
import top.oahnus.enums.CourseState;

import java.util.List;

/**
 * Created by oahnus on 2017/4/8
 * 21:22.
 */
public interface CourseSelectionService {
    Page<List<CourseSelection>> selectCourseSelectionByCourseId(String courseId, CourseState courseState, Integer page, Integer limit);
    Page<List<CourseSelection>> selectCourseSelectionByStudentId(String studentId, CourseState courseState, Integer page, Integer limit);

    CourseSelection insertNewCourseSelection(CourseSelectionDto courseSelectionDto);
    Integer deleteCourseSelectionById(String courseSelectionId);
}
