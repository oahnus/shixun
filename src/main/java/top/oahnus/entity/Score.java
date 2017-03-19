package top.oahnus.entity;

import lombok.Data;

/**
 * Created by oahnus on 2017/3/19 20:08.
 * 分数
 */
@Data
public class Score {
    private String id;
    private String courseId;
    private String studentId;
    private Float teacherScore;
    private Float companyScore;

    public Score() {}

    public Score(String courseId, String studentId, Float teacherScore, Float companyScore) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.teacherScore = teacherScore;
        this.companyScore = companyScore;
    }
}
