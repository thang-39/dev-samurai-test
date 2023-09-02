package vn.devsamurai.codingchallenge.taskmanagement.consumers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskMessage;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;
import vn.devsamurai.codingchallenge.taskmanagement.service.TaskService;

@Service
public class TaskConsumer {

    @Autowired
    private TaskService taskService;

    @KafkaListener(topics = "newTask1",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload TaskMessage taskMessage, Acknowledgment ack) {
        taskService.save(taskMessage);
        ack.acknowledge();
    }

    @KafkaListener(topics = "testString",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload String str, Acknowledgment ack) {
        System.out.println("consumer consume the message: " + str);
        ack.acknowledge();
    }
}
