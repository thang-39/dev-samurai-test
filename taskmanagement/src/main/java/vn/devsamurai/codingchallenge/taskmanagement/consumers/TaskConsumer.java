package vn.devsamurai.codingchallenge.taskmanagement.consumers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
    @Retryable(
            value = Exception.class, // Specify the exception(s) for which retry should be attempted
            maxAttempts = 3, // Maximum number of retry attempts
            backoff = @Backoff(delay = 1000) // Delay between retry attempts (in milliseconds)
    )
    public void consume(@Payload TaskMessage taskMessage, Acknowledgment ack) {
        try {
            taskService.save(taskMessage);
            ack.acknowledge();
        } catch (Exception e) {
            throw e; // Rethrow the exception to trigger the retry mechanism
        }
    }
}
