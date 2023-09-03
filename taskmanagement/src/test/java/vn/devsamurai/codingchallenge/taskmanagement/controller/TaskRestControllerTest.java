package vn.devsamurai.codingchallenge.taskmanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.service.impl.TaskServiceImpl;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskRestController.class)
@AutoConfigureMockMvc
class TaskRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskServiceImpl taskService;

    @Test
    void updateTask_BlankContent_ShouldReturnBadRequest() throws Exception {
        String taskId = "taskId";
        String requestContent = "{\"content\": \"\", \"startDate\": \"2023-09-01T15:30:00\", \"dueDate\": \"2023-09-05T15:30:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/task/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void updateTask_DueDateBeforeStartDate_ShouldReturnBadRequest() throws Exception {
        String taskId = "taskId";
        String requestContent = "{\"content\": \"Task content\", \"startDate\": \"2023-09-05T10:00:00\", \"dueDate\": \"2023-09-01T15:30:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/task/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void updateTask_ValidRequest_ShouldReturnUpdatedTask() throws Exception {
        String taskId = "taskId";
        String requestContent = "{\"content\": \"Task content\", \"startDate\": \"2023-09-01T10:00:00\", \"dueDate\": \"2023-09-05T15:30:00\"}";
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setContent("Task content");
        requestDto.setStartDate(LocalDateTime.parse("2023-09-01T10:00:00"));
        requestDto.setDueDate(LocalDateTime.parse("2023-09-05T15:30:00"));

        TaskResponseDto updatedTask = new TaskResponseDto();
        updatedTask.setId(taskId);
        updatedTask.setContent("Task content");
        updatedTask.setStartDate(LocalDateTime.parse("2023-09-01T10:00:00"));
        updatedTask.setDueDate(LocalDateTime.parse("2023-09-05T15:30:00"));

        when(taskService.updateTask(any(), any())).thenReturn(updatedTask);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/task/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(taskId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Task content"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2023-09-01 10:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("2023-09-05 15:30:00"));
    }


}