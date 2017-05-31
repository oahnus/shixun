package top.oahnus.entity;

import lombok.Data;
import top.oahnus.payload.CourseSelectionDto;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 选课信息
 */
@Data
public class CourseSelection {
    private Long id;
    private Course course = new Course();
    private Student student = new Student();
    private Date createTime;
    private Date editTime;

    public CourseSelection() {}

    public CourseSelection(CourseSelectionDto courseSelectionDto) {
        this.id = courseSelectionDto.getId();
        this.course.setId(courseSelectionDto.getCourseId());
        this.student.setId(courseSelectionDto.getStudentId());
    }
}
