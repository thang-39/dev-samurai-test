package vn.devsamurai.codingchallenge.taskmanagement.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskMessage;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;
import vn.devsamurai.codingchallenge.taskmanagement.exception.TaskNotFoundException;
import vn.devsamurai.codingchallenge.taskmanagement.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TaskServiceImpl taskService;

    static TaskMessage taskMessage1;
    static Task task1;
    static Task task2;
    @BeforeEach
    void setUp() {
        taskMessage1 = TaskMessage.builder()
                .assigner("Boss")
                .assignee("Employee")
                .startDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(7))
                .content("simple task")
                .build();

        task1 = Task.builder()
                .id("id")
                .createdDate(LocalDateTime.now())
                .assigner("Boss")
                .assignee("Employee")
                .startDate(LocalDateTime.now().plusDays(1))
                .dueDate(LocalDateTime.now().plusDays(7))
                .content("simple task")
                .isCompleted(false)
                .build();

        task2 = Task.builder()
                .id("id2")
                .createdDate(LocalDateTime.now())
                .assigner("Boss2")
                .assignee("Employee2")
                .startDate(LocalDateTime.now().plusDays(2))
                .dueDate(LocalDateTime.now().plusDays(8))
                .content("simple task 2")
                .isCompleted(false)
                .build();
    }

    @Test
    void save_ShouldSaveTask() {

        taskService.save(taskMessage1);

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void getTask_ValidId_ShouldReturnTaskResponseDto() {

        when(taskRepository.findById(any())).thenReturn(Optional.of(task1));

        TaskResponseDto responseDto = taskService.getTask(any());

        assertNotNull(responseDto);
        assertEquals("id", responseDto.getId());
        assertEquals("Boss", responseDto.getAssigner());
        assertEquals("Employee", responseDto.getAssignee());
    }

    @Test
    void getTask_InvalidId_ShouldThrowTaskNotFoundException() {
        String taskId = "nonExistentTaskId";

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTask(taskId);
        });
    }

    @Test
    void updateTask_ValidId_ShouldReturnUpdatedTaskResponseDto() {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setAssigner("Updated Assigner");
        taskRequestDto.setAssignee("Updated Assignee");


        when(taskRepository.findById(any())).thenReturn(Optional.of(task1));
        when(taskRepository.save(any(Task.class))).thenReturn(task1);

        TaskResponseDto responseDto = taskService.updateTask(any(), taskRequestDto);

        assertNotNull(responseDto);
        assertEquals("id", responseDto.getId());
        assertEquals("Updated Assigner", responseDto.getAssigner());
        assertEquals("Updated Assignee", responseDto.getAssignee());
    }

    @Test
    void deleteTask_ValidId_ShouldDeleteTask() {
        taskService.deleteTask(any());

        verify(taskRepository, times(1)).deleteById(any());
    }

    @Test
    void deleteTask_TaskNotInRepository_ShouldThrowTaskNotFoundException() {
        doAnswer((Answer<Void>) invocation -> {
            throw new EmptyResultDataAccessException("Can not delete non-existing resource", 1);
        }).when(taskRepository).deleteById(anyString());

        assertThrows(EmptyResultDataAccessException.class, () -> taskService.deleteTask(anyString()));
    }

    @Test
    void getAllTasks_ShouldReturnListOfTaskResponseDto() {

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<TaskResponseDto> responseDtoList = taskService.getAllTasks();

        assertNotNull(responseDtoList);
        assertEquals(2, responseDtoList.size());

    }

    @Test
    void updateTaskStatus_ValidId_ShouldReturnUpdatedStatus() {

        when(taskRepository.findById(any())).thenReturn(Optional.of(task1));
        when(taskRepository.save(any(Task.class))).thenReturn(task1);

        boolean updatedStatus = taskService.updateTaskStatus(any(), true);

        assertTrue(updatedStatus);
        assertTrue(task1.isCompleted());
    }

    @Test
    void findTaskRequestDtoById_ValidId_ShouldReturnTaskRequestDto() {

        when(taskRepository.findById(any())).thenReturn(Optional.of(task1));

        TaskRequestDto requestDto = taskService.findTaskRequestDtoById(any());

        assertNotNull(requestDto);
        assertEquals("id", requestDto.getId());
        assertEquals("Boss", requestDto.getAssigner());
        assertEquals("Employee", requestDto.getAssignee());

    }

    @Test
    void findTaskRequestDtoById_InvalidId_ShouldThrowTaskNotFoundException() {

        when(taskRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.findTaskRequestDtoById(any());
        });
    }


}

