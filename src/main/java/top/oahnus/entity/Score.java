package top.oahnus.entity;

import lombok.Data;

/**
 * Created by oahnus on 2017/3/19 20:08.
 * 分数
 */
@Data
public class Score {
    private String id;
    private Course course;
    private Student student;
    private Float teacherScore;
    private Float companyScore;
}
