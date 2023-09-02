package vn.devsamurai.codingchallenge.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.devsamurai.codingchallenge.taskmanagement.validation.DueDateAfterStart;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DueDateAfterStart
public class TaskRequestDto {

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdDate;

    private String assigner;
    private String assignee;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    @NotBlank
    private String content;

//    @NotBlank
//    private boolean isCompleted;
}
