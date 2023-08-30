package vn.devsamurai.codingchallenge.taskmanagement.service;

import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;

public interface TaskService {
    void save(Task task);

    Task getTask(String id);
}
