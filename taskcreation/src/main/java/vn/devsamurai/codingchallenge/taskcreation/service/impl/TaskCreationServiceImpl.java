package vn.devsamurai.codingchallenge.taskcreation.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskcreation.entity.Task;
import vn.devsamurai.codingchallenge.taskcreation.service.TaskCreationService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskCreationServiceImpl implements TaskCreationService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public TaskResponseDto create(TaskRequestDto taskRequestDto) {
        Task newTask = Task.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .assigner(taskRequestDto.getAssigner())
                .assignee(taskRequestDto.getAssignee())
                .startDate(taskRequestDto.getStartDate())
                .dueDate(taskRequestDto.getDueDate())
                .content(taskRequestDto.getContent())
                .build();

        kafkaTemplate.send("newTask1", newTask);

        TaskResponseDto response = new TaskResponseDto();
        BeanUtils.copyProperties(newTask, response);
        return response;
    }

}
