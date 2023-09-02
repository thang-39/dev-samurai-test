package vn.devsamurai.codingchallenge.taskcreation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import vn.devsamurai.codingchallenge.taskcreation.validation.DueDateAfterStart;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@DueDateAfterStart
public class TaskRequestDto {

    private String id;

    private String assigner;
    private String assignee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    @NotBlank(message = "Content can not be blank")
    private String content;
}
