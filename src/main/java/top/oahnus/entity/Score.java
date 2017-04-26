package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.ScoreDto;

/**
 * Created by oahnus on 2017/4/25
 * 12:29.
 */
@Data
public class Score {
    private String id;
    private Student student = new Student();
    private CourseSelection courseSelection = new CourseSelection();
    private Float teacherScore;
    private Float companyScore;

    public Score() {}

    public Score(ScoreDto scoreDto) {
        this.id = scoreDto.getId();
        this.student.setId(scoreDto.getStudentId());
        Course course = new Course();
        course.setId(scoreDto.getCourseSelectionId());
        this.courseSelection.setCourse(course);
    }
}
