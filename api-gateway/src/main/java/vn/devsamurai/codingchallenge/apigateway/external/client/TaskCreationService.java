package vn.devsamurai.codingchallenge.apigateway.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.devsamurai.codingchallenge.apigateway.external.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.apigateway.external.dto.TaskResponseDto;

import javax.validation.Valid;

@FeignClient(name = "task-creation",
        url = "${microservices.taskCreation}")
public interface TaskCreationService {
    @PostMapping("/create")
    ResponseEntity<TaskResponseDto> create(@RequestBody TaskRequestDto taskRequestDto);

}
