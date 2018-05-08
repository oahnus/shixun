package top.oahnus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.entity.TaskResult;

import java.util.List;

/**
 * Created by oahnus on 2017/4/19
 * 16:46.
 */
@Mapper
@Repository
public interface TaskResultMapper {
    List<TaskResult> selectTaskResult(@Param("courseId")String courseId, @Param("studentId")String studentId,@Param("taskId")String taskId);
    TaskResult selectTaskResultById(@Param("taskResultId")String taskResultId);

    Integer deleteTaskResult(@Param("taskResultId")String taskResultId);
    Integer insertNewTaskResult(TaskResult taskResult);
    Integer updateTaskResult(TaskResult taskResult);
}
