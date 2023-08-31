package vn.devsamurai.codingchallenge.taskmanagement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskResponseDto;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;
import vn.devsamurai.codingchallenge.taskmanagement.exception.TaskNotFoundException;
import vn.devsamurai.codingchallenge.taskmanagement.repository.TaskRepository;
import vn.devsamurai.codingchallenge.taskmanagement.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public TaskResponseDto getTask(String id) {

        Task task = unwarpTask(taskRepository.findById(id), id);

        TaskResponseDto responseDto = new TaskResponseDto();

        BeanUtils.copyProperties(task, responseDto);

        return responseDto;
    }

    @Override
    public TaskResponseDto updateTask(String id, TaskRequestDto taskRequestDto) {
        Task task = unwarpTask(taskRepository.findById(id), id);

        task.setCreatedDate(LocalDateTime.now());
        task.setAssignee(taskRequestDto.getAssignee());
        task.setAssigner(taskRequestDto.getAssigner());
        task.setDueDate(taskRequestDto.getDueDate());
        task.setStartDate(taskRequestDto.getStartDate());
        task.setContent(taskRequestDto.getContent());

        taskRepository.save(task);

        TaskResponseDto responseDto = new TaskResponseDto();
        BeanUtils.copyProperties(task, responseDto);
        return responseDto;
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();

        return taskList.stream().map(task -> {
            TaskResponseDto dto = new TaskResponseDto();
            BeanUtils.copyProperties(task, dto);
            return dto;
        }).toList();
    }

    static Task unwarpTask(Optional<Task> entity, String id) {
        if (entity.isPresent()) return entity.get();
        throw new TaskNotFoundException(id);
    }
}
