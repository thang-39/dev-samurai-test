package vn.devsamurai.codingchallenge.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDtoFromCreation;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;

import vn.devsamurai.codingchallenge.taskmanagement.exception.TaskNotFoundException;
import vn.devsamurai.codingchallenge.taskmanagement.service.TaskService;

import java.util.List;
import java.util.Objects;

@Controller
public class TaskViewController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.taskCreation}")
    private String taskCreationServiceUrl;

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public String taskList(Model model) {
        List<TaskResponseDto> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/create")
    public String createTask(Model model,
                             @RequestParam(required = false) String id) {

        TaskRequestDto request;

        if (id == null) {
            request = new TaskRequestDto();
        } else {
            try {
                request = taskService.findTaskRequestDtoById(id);
            } catch (TaskNotFoundException ex) {
                request = new TaskRequestDto();
            }
        }

        model.addAttribute("task", request);
        return "createTask";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(TaskRequestDto task) {
        if (Objects.equals(task.getId(), "")) {
            ResponseEntity<TaskResponseDtoFromCreation> response = restTemplate
                    .postForEntity(
                            taskCreationServiceUrl,
                            task,
                            TaskResponseDtoFromCreation.class);
        } else {
            taskService.updateTask(task.getId(), task);
        }
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam String id) {
        taskService.deleteTask(id);
        return "redirect:/list";
    }

    @GetMapping("/complete/{id}/{status}")
    public String updateTaskStatus(@PathVariable String id,
                                   @PathVariable boolean status) {
        taskService.updateTaskStatus(id, status);
        return "redirect:/list";
    }






}
