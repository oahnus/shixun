package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.domain.Course;
import top.oahnus.domain.Task;
import top.oahnus.enums.CourseState;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.NoAuthException;
import top.oahnus.repository.CourseRepo;
import top.oahnus.repository.TaskRepo;

import java.util.Date;
import java.util.List;

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
            throw new NoAuthException("");
        }
        return taskRepo.findByCourseIdAndCreateUserIdAndDelFlagFalse(courseId, authId);
    }

    public void save(Task task, Long authId) {
        checkExisted(task, authId);
        task.setCreateTime(new Date());
        taskRepo.save(task);
    }

    public void update(Task task, Long authId) {
        checkExisted(task, authId);

        task.setUpdateTime(new Date());
        taskRepo.save(task);
    }

    private void checkExisted(Task task, Long authId) {
        Long courseId = task.getCourseId();
        Course course = courseRepo.findOne(courseId);

        if (course == null || course.getDelFlag()) {
            throw new BadRequestParamException("");
        }

        if (task.getCreateUserId().equals(authId)) {
            throw new NoAuthException("");
        }

        CourseState state = course.getState();

        if (state.equals(CourseState.COURSE_END)) {
            throw new BadRequestParamException("");
        }
    }

    public void delete(Long taskId, Long authId) {
        Task task = taskRepo.findOne(taskId);

        if (!task.getCreateUserId().equals(authId)) {
            throw new NoAuthException("");
        }

        task.setDelFlag(false);
        taskRepo.save(task);
    }
}
