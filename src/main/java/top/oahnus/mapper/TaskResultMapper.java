package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oahnus.entity.TaskResult;

import java.util.List;

/**
 * Created by oahnus on 2017/4/19
 * 16:46.
 */
@Mapper
public interface TaskResultMapper {
    List<TaskResult> selectTaskResultByStudentId(@Param("studentId")String studentId);
    Integer deleteTaskResult(@Param("taskResultId")String taskResultId);
    Integer insertNewTaskResult(TaskResult taskResult);
}
