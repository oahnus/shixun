package top.oahnus.mymapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by oahnus on 2018/5/9
 * 16:02.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
