package top.oahnus.service;

import top.oahnus.dto.TaskResultDto;
import top.oahnus.entity.TaskResult;

import java.util.List;

/**
 * Created by oahnus on 2017/4/24
 * 13:36.
 */
public interface TaskResultService {
    TaskResult selectTaskResultById(String taskResultId);
    List<TaskResult> selectTaskResult(String courseId, String studentId, String taskId);

    Integer deleteTaskResult(String taskResultId);
    Integer insertNewTaskResult(TaskResultDto taskResultDto);
    Integer updateTaskResult(TaskResultDto taskResultDto);
}
