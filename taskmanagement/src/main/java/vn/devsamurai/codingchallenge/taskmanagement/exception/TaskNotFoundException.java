package vn.devsamurai.codingchallenge.taskmanagement.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String id) {
        super("This task id '" + id + "' does not exist in our records");
    }
}
