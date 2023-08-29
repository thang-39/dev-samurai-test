package vn.devsamurai.codingchallenge.taskcreation.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequest;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponse;
import vn.devsamurai.codingchallenge.taskcreation.entity.Task;
import vn.devsamurai.codingchallenge.taskcreation.service.TaskCreationService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskCreationServiceImpl implements TaskCreationService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public TaskResponse create(TaskRequest taskRequest) {
        Task newTask = Task.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .assigner(taskRequest.getAssigner())
                .assignee(taskRequest.getAssignee())
                .startDay(taskRequest.getStartDay())
                .dueDay(taskRequest.getDueDay())
                .content(taskRequest.getContent())
                .build();

        System.out.println("task: " + newTask);
        kafkaTemplate.send("newTask1", newTask);

        TaskResponse response = new TaskResponse();
        BeanUtils.copyProperties(newTask, response);
        System.out.println("response: " + response);
        return response;
    }

    @Override
    public void test(String str) {
        kafkaTemplate.send("testString", str);
    }
}
