package vn.devsamurai.codingchallenge.taskmanagement.service;

import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;

import java.util.List;

public interface TaskService {
    void save(Task task);

    TaskResponseDto getTask(String id);

    TaskResponseDto updateTask(String id, TaskRequestDto taskRequestDto);

    void deleteTask(String id);

    List<TaskResponseDto> getAllTasks();
}
