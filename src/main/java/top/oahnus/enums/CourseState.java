package top.oahnus.enums;

import java.util.Arrays;

/**
 * Created by oahnus on 2017/2/22.
 * 课程状态
 */
public enum CourseState {
    COURSE_INIT(0),
    // 开始选课
    ON_SELECTED(1),
    // 结束选课
    OFF_SELECTED(2),
    // 已开课
    COURSE_START(3),
    // 已结课
    COURSE_END(4);

    private int code;

    CourseState(int code) {
        this.code = code;
    }

    public static CourseState getState(int code){
        for (CourseState state: CourseState.values()) {
            if(state.getCode() == code) {
                return state;
            }
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }
}
