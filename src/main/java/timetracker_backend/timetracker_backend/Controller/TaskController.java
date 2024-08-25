package timetracker_backend.timetracker_backend.Controller;

import java.util.List;

import timetracker_backend.timetracker_backend.Model.Task;
import timetracker_backend.timetracker_backend.Service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @PutMapping("/{id}/complete")
    public Task completeTask(@PathVariable String id, @RequestBody Task completedTask) {
        return taskService.completeTask(id, completedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        System.out.println("Tar bort uppgift med namn: " + id);
        taskService.deleteTask(id);
    }

}
