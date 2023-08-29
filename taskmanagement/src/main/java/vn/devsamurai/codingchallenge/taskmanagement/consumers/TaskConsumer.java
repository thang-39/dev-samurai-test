package vn.devsamurai.codingchallenge.taskmanagement.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import vn.devsamurai.codingchallenge.taskmanagement.entity.TaskMessage;

@Service
public class TaskConsumer {

    @KafkaListener(topics = "newTask1",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload TaskMessage task, Acknowledgment ack) {
        System.out.println("consumer consume the message: " + task);
        ack.acknowledge();
    }

    @KafkaListener(topics = "testString",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload String str, Acknowledgment ack) {
        System.out.println("consumer consume the message: " + str);
        ack.acknowledge();
    }
}
