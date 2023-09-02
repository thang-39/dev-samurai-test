package vn.devsamurai.codingchallenge.taskcreation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskcreation.service.TaskCreationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/task")
public class TaskCreationController {

    @Autowired
    private TaskCreationService service;

    @PostMapping("/create")
    public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        System.out.println(taskRequestDto);
        return new ResponseEntity<>(service.create(taskRequestDto), HttpStatus.CREATED);
    }
}
