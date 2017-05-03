package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.TaskResultDto;
import top.oahnus.entity.CourseTask;
import top.oahnus.entity.TaskResult;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.exception.SQLExecuteFailedExceeption;
import top.oahnus.exception.TimeSpanException;
import top.oahnus.mapper.CourseTaskMapper;
import top.oahnus.mapper.TaskResultMapper;
import top.oahnus.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2017/4/24
 * 16:33.
 */
@Service
public class TaskResultService {
    @Autowired
    private TaskResultMapper taskResultMapper;
    @Autowired
    private CourseTaskMapper courseTaskMapper;

    public TaskResult selectTaskResultById(String taskResultId) {
        if (StringUtil.isEmpty(taskResultId)) {
            throw new BadRequestParamException("任务成果Id不能为空");
        }
        TaskResult taskResult = taskResultMapper.selectTaskResultById(taskResultId);
        if (taskResult == null) {
            throw new NotFoundException("数据为找到");
        }
        return taskResult;
    }

    public List<TaskResult> selectTaskResult(String courseId, String studentId, String taskId) {
        if (StringUtil.isEmpty(taskId) && StringUtil.isEmpty(courseId) && StringUtil.isEmpty(studentId)) {
            throw new BadRequestParamException("请求参数错误");
        }
        List<TaskResult> results = taskResultMapper.selectTaskResult(courseId, studentId, taskId);
        return results;
    }

    public Integer deleteTaskResult(String taskResultId) {
        if (StringUtil.isEmpty(taskResultId)) {
            throw new BadRequestParamException("任务成果id不能为空");
        }
        Integer count = taskResultMapper.deleteTaskResult(taskResultId);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("删除数据失败");
        } else if (count == 0) {
            throw new NotFoundException("数据为找到");
        } else {
            return count;
        }
    }

    public Integer insertNewTaskResult(TaskResultDto taskResultDto) {
        if (!isCurrentDateTimeInTaskDuration(taskResultDto.getTaskId())) {
            throw new TimeSpanException("当前时间已超过任务成果截止时间");
        }
        TaskResult taskResult = new TaskResult(taskResultDto);
        Integer count = taskResultMapper.insertNewTaskResult(taskResult);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("插入数据失败");
        } else {
            return count;
        }
    }

    public Integer updateTaskResult(TaskResultDto taskResultDto) {
        if (StringUtil.isEmpty(taskResultDto.getId())) {
            throw new BadRequestParamException("任务成果id不能为空");
        }
        if (!isCurrentDateTimeInTaskDuration(taskResultDto.getTaskId())) {
            throw new TimeSpanException("当前时间已超过任务成果截止时间");
        }
        TaskResult taskResult = new TaskResult(taskResultDto);
        Integer count = taskResultMapper.updateTaskResult(taskResult);
        if (count < 0) {
            throw new SQLExecuteFailedExceeption("更新数据失败");
        } else if (count == 0) {
            throw new NotFoundException("数据未找到");
        } else {
            return count;
        }
    }

    /**
     * 判断当前时间是否在任务的开始结束时间之间
     */
    private boolean isCurrentDateTimeInTaskDuration(String taskId) {
        CourseTask task = courseTaskMapper.selectCourseTaskById(taskId);
        Date deadline = task.getDeadline();
        Date now = new Date();
        return now.before(deadline);
    }
}