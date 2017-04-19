package top.oahnus.mapper;

import java.util.Map;

/**
 * Created by oahnus on 2017/4/8
 * 9:47.
 */
public interface BaseMapper {
    Integer selectRecordCount(Map<String,String> param);
}
