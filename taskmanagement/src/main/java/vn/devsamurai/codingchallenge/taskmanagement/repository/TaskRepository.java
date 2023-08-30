package vn.devsamurai.codingchallenge.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.devsamurai.codingchallenge.taskmanagement.entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
}
