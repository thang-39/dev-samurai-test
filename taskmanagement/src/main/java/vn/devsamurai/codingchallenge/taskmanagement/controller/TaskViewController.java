package vn.devsamurai.codingchallenge.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDtoFromCreation;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;

import vn.devsamurai.codingchallenge.taskmanagement.service.TaskService;

import java.util.List;

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
    public String createTask(Model model) {
        model.addAttribute("task", new TaskRequestDto());
        return "createTask";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(TaskRequestDto task) {
        ResponseEntity<TaskResponseDtoFromCreation> response = restTemplate
                .postForEntity(
                        taskCreationServiceUrl,
                        task,
                        TaskResponseDtoFromCreation.class);
        System.out.println(response);
        return "redirect:/list";
    }




}
