package top.oahnus.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2017/4/19
 * 17:13.
 */
@Data
public class Review {
    private String id;
    private String target_user_id;
    private String target_type;
    private String target_course_id;
    private String content;
    private Date create_time;
}
