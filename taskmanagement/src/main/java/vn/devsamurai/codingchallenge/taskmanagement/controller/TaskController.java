package vn.devsamurai.codingchallenge.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;
import vn.devsamurai.codingchallenge.taskmanagement.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task)
}
