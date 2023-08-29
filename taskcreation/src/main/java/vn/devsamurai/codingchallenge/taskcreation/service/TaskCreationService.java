package vn.devsamurai.codingchallenge.taskcreation.service;

import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequest;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponse;

public interface TaskCreationService {
    TaskResponse create(TaskRequest taskRequest);

    void test(String str);
}
