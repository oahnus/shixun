package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.CourseSelectionDto;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 选课信息
 */
@Data
public class CourseSelection {
    private String id;
    private Course course;
    private Student student;
    private Date createTime;
    private Date editTime;
    private Float teacherScore;
    private Float companyScore;

    public CourseSelection() {}

    public CourseSelection(CourseSelectionDto courseSelectionDto) {
        this.id = courseSelectionDto.getId();
        this.course.setId(courseSelectionDto.getCourseId());
        this.student.setId(courseSelectionDto.getStudentId());
    }
}
