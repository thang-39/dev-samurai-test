package vn.devsamurai.codingchallenge.taskmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;
import vn.devsamurai.codingchallenge.taskmanagement.repository.TaskRepository;
import vn.devsamurai.codingchallenge.taskmanagement.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getTask(String id) {
        return taskRepository.findById(id).get();
    }
}
