package vn.devsamurai.codingchallenge.taskmanagement.service;

import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskMessage;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;

import java.util.List;

public interface TaskService {
    void save(TaskMessage task);

    TaskResponseDto getTask(String id);

    TaskResponseDto updateTask(String id, TaskRequestDto taskRequestDto);

    void deleteTask(String id);

    List<TaskResponseDto> getAllTasks();

    Boolean updateTaskStatus(String id, boolean status);

}
