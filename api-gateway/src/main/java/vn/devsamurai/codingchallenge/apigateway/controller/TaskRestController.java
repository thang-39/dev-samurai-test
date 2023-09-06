package vn.devsamurai.codingchallenge.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.devsamurai.codingchallenge.apigateway.external.client.TaskCreationService;
import vn.devsamurai.codingchallenge.apigateway.external.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.apigateway.external.dto.TaskResponseDto;

import javax.validation.Valid;

@RestController
public class TaskRestController {

    @Autowired
    private TaskCreationService creationService;

    @PostMapping("/test")
    public ResponseEntity<TaskResponseDto> test(@Valid @RequestBody TaskRequestDto dto) {
        return creationService.create(dto);
    }

    @GetMapping
    public String hello() {
        return "hello";
    }
}
