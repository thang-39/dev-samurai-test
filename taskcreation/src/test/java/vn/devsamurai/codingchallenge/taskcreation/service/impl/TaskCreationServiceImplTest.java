package vn.devsamurai.codingchallenge.taskcreation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskcreation.entity.Task;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskCreationServiceImplTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private TaskCreationServiceImpl taskCreationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        // Prepare test data
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setAssigner("John");
        requestDto.setAssignee("Jane");
        requestDto.setStartDate(LocalDateTime.now());
        requestDto.setDueDate(LocalDateTime.now().plusDays(7));
        requestDto.setContent("Sample task content");

        // Call the service method
        TaskResponseDto responseDto = taskCreationService.create(requestDto);

        // Verify the response
        assertEquals(requestDto.getAssigner(), responseDto.getAssigner());
        assertEquals(requestDto.getAssignee(), responseDto.getAssignee());
        assertEquals(requestDto.getStartDate(), responseDto.getStartDate());
        assertEquals(requestDto.getDueDate(), responseDto.getDueDate());
        assertEquals(requestDto.getContent(), responseDto.getContent());

        // Verify Kafka message sent
        verify(kafkaTemplate).send(eq("newTask1"), any(Task.class));
    }

}