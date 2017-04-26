package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.ReviewDto;
import top.oahnus.enums.AuthType;

import java.util.Date;

/**
 * Created by oahnus on 2017/4/19
 * 17:13.
 * 双向评价
 */
@Data
public class Review {
    private String id;
    private String fromUserUsername;
    private String fromUserName;
    private AuthType fromUserType;
    private String toUserUserName;
    private String toUsername;
    private AuthType toUserType;
    private String courseId;
    private String courseName;
    private String content;
    private Date createTime;

    public Review() {}

    public Review(ReviewDto reviewDto) {
        this.fromUserUsername = reviewDto.getFromUserName();
        this.fromUserName = reviewDto.getFromUserUsername();
        this.fromUserType = AuthType.valueOf(reviewDto.getFromUserType());
        this.toUserUserName = reviewDto.getToUsername();
        this.toUsername = reviewDto.getToUserUsername();
        this.toUserType = AuthType.valueOf(reviewDto.getToUserType());
        this.courseId = reviewDto.getCourseId();
        this.courseName = reviewDto.getCourseName();
        this.content = reviewDto.getContent();
    }
}
