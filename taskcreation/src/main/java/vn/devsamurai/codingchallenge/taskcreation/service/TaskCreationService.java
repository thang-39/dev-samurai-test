package vn.devsamurai.codingchallenge.taskcreation.service;

import vn.devsamurai.codingchallenge.taskcreation.dto.TaskRequestDto;
import vn.devsamurai.codingchallenge.taskcreation.dto.TaskResponseDto;

public interface TaskCreationService {
    TaskResponseDto create(TaskRequestDto taskRequestDto);

}
