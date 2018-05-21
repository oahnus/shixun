package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.oahnus.domain.Course;
import top.oahnus.domain.Task;
import top.oahnus.enums.CourseState;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NoAuthException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.repository.CourseRepo;
import top.oahnus.repository.TaskRepo;
import top.oahnus.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by oahnus on 2018/5/14
 * 10:10.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private CourseRepo courseRepo;

    public List<Task> list(Long courseId){
        return taskRepo.findByCourseIdAndDelFlagFalse(courseId);
    }

    public List<Task> listByAuth(Long courseId, Long authId) {
        if (authId == null) {
            throw new NoAuthException("Auth Error");
        }
        return taskRepo.findByCourseIdAndCreateUserIdAndDelFlagFalse(courseId, authId);
    }

    public void save(Task task, Long authId) {
        checkExisted(task, authId);
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        task.setCreateUserId(authId);
        taskRepo.save(task);
    }

    public void update(Task task, Long authId) {
        Long taskId = task.getId();
        Task t = taskRepo.getOne(taskId);
        if (t == null) {
            throw new NotFoundException("");
        }
        checkExisted(task, authId);

        task.setName(task.getName());
        task.setContent(task.getContent());
        task.setStartTime(task.getStartTime());
        task.setEndTime(task.getEndTime());
        task.setMemo(task.getMemo());
        task.setUpdateTime(new Date());

        taskRepo.save(task);
    }

    private void checkExisted(Task task, Long authId) {
        Long courseId = task.getCourseId();
        Course course = courseRepo.findOne(courseId);

        if (course == null || course.getDelFlag()) {
            throw new BadRequestParamException("Bad Params");
        }

        if (task.getCreateUserId().equals(authId)) {
            throw new NoAuthException("Auth Error");
        }

        CourseState state = course.getState();

        if (state.equals(CourseState.COURSE_END)) {
            throw new BadRequestParamException("Bad Params");
        }

        List<Task> taskList = taskRepo.findByCourseIdAndDelFlagFalse(courseId);
        Date start = task.getStartTime();
        Date end = task.getEndTime();
        boolean hasExistedTask = taskList.stream()
                .anyMatch(_task -> {
                    Date _start = _task.getStartTime();
                    Date _end = _task.getEndTime();

                    return (DateUtils.isBetween(start, _start, _end) || DateUtils.isBetween(end, _start, _end))
                            && !_task.getId().equals(task.getId());
                });

        if (hasExistedTask) {
            throw new BadRequestParamException("Bad Params");
        }
    }

    public void delete(Long taskId, Long authId) {
        Task task = taskRepo.findOne(taskId);

        if (!task.getCreateUserId().equals(authId)) {
            throw new NoAuthException("Auth Error");
        }

        task.setDelFlag(false);
        taskRepo.save(task);
    }
}
