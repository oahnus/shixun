package top.oahnus.enums;

import java.util.Arrays;

/**
 * Created by oahnus on 2017/2/22.
 * 课程状态
 */
public enum CourseState {
    // 开始选课
    ON_SELECTED,
    // 结束选课
    OFF_SELECTED,
    // 已开课
    COURSE_START,
    // 已结课
    COURSE_END;

    public static CourseState getState(int code){
        for (CourseState state: CourseState.values()) {
            if(state.ordinal() == code) {
                return state;
            }
        }
        return null;
    }
}
