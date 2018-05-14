package top.oahnus.common.payload.pageForm;

import lombok.Data;

/**
 * Created by oahnus on 2018/5/14
 * 11:29.
 */
@Data
public class SelectionRecordForm {
    private PageForm page;
    private Long studentId;
    private Long courseId;
}
