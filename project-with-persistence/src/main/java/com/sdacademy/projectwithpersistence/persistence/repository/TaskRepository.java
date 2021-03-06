package com.sdacademy.projectwithpersistence.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sdacademy.projectwithpersistence.persistence.model.Task;
import com.sdacademy.projectwithpersistence.persistence.model.TaskStatus;

public interface TaskRepository extends CrudRepository<Task, Long> {

	// we use methods with more complex derived queries
	List<Task> findByAssigneeFirstName(String name);

	List<Task> findByDueDateBeforeAndStatusEquals(LocalDate dueDate, TaskStatus status);

}
