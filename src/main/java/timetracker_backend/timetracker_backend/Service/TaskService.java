package timetracker_backend.timetracker_backend.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timetracker_backend.timetracker_backend.Model.Task;
import timetracker_backend.timetracker_backend.Repository.TaskRepository;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;


@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        ZonedDateTime localDateTime = task.getStartDate().toInstant().atZone(ZoneId.systemDefault());
        task.setStartDate(Date.from(localDateTime.toInstant()));
        return task;
    }

    public Task createTask(Task task) {
        ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusHours(2);
        task.setStartDate(Date.from(utcDateTime.toInstant()));
        task.setIsCompleted(false);
        logger.info("Saving task with start date: " + task.getStartDate());
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskName(updatedTask.getTaskName());
        task.setIsCompleted(updatedTask.getIsCompleted());
        task.setStartDate(updatedTask.getStartDate());
        task.setStartTime(updatedTask.getStartTime());
        task.setTrackedTime(updatedTask.getTrackedTime());

        return taskRepository.save(task);
    }

    public Task completeTask(String id, Task completedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setIsCompleted(true);
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

}
