package vn.devsamurai.codingchallenge.taskcreation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskcreation.service.impl.TaskCreationServiceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(TaskCreationController.class)
@AutoConfigureMockMvc
class TaskCreationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskCreationServiceImpl taskCreationService;

    @Test
    void create_task_test() throws Exception {
        // data
        TaskRequestDto requestDto = TaskRequestDto.builder()
                .assigner("boss")
                .assignee("employee")
                .startDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(7))
                .content("simple task")
                .build();

        TaskResponseDto responseDto = TaskResponseDto.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .assigner(requestDto.getAssigner())
                .assignee(requestDto.getAssignee())
                .startDate(requestDto.getStartDate())
                .dueDate(requestDto.getDueDate())
                .content(requestDto.getContent())
                .build();

        // Mock service method
        when(taskCreationService.create(any(TaskRequestDto.class)))
                .thenReturn(responseDto);

        // Perform POST request
        mockMvc.perform(post("/api/v1/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.content").value(requestDto.getContent()));

    }
}