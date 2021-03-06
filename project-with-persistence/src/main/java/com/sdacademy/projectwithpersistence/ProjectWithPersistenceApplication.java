package com.sdacademy.projectwithpersistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sdacademy.projectwithpersistence.persistence.model.Project;
import com.sdacademy.projectwithpersistence.persistence.model.Task;
import com.sdacademy.projectwithpersistence.persistence.model.TaskStatus;
import com.sdacademy.projectwithpersistence.persistence.model.User;
import com.sdacademy.projectwithpersistence.persistence.repository.ProjectRepository;
import com.sdacademy.projectwithpersistence.persistence.repository.TaskRepository;
import com.sdacademy.projectwithpersistence.persistence.repository.UserRepository;

@SpringBootApplication
public class ProjectWithPersistenceApplication implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectWithPersistenceApplication.class);

	@Autowired
	EntityManager entityManager;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectWithPersistenceApplication.class, args);
	}

	@Override
	// @Transactional
	public void run(ApplicationArguments args) throws Exception {
		// we display in several logs all the data in the tables
		Iterable<Project> allProjects = projectRepository.findAll();
		LOG.info("All Projects:\n{}", allProjects);
		Iterable<Task> allTasks = taskRepository.findAll();
		LOG.info("All Tasks:\n{}", allTasks);
		Iterable<User> allUser = userRepository.findAll();
		LOG.info("All Users:\n{}", allUser);

		// we display a task and the number of users
		Optional<Task> task1 = taskRepository.findById(1L);
		LOG.info("Task by id 1:\n{}", task1);
		long noOfUsers = userRepository.count();
		LOG.info("Number of users:\n{}", noOfUsers);

		// we use methods with derived queries
		Optional<Project> project1 = projectRepository.findByCodeEquals("P1");
		LOG.info("Project with code P1: \n{}", project1);

		int projectCount = projectRepository.countByName("Project 1");
		LOG.info("Number of projects with name 'Project 1':\n{}", projectCount);

		// we use methods with more complex derived queries
		Iterable<Project> projects = projectRepository.findByNameStartingWith("Project");
		LOG.info("Projects name starting with Project:");
		projects.forEach((project) -> LOG.info("{}", project));

		Iterable<Project> distinctProjects = projectRepository.findDistinctByTasksNameContaining("Task");
		LOG.info("Distinct projects with Task name containing \"Task\"\n{}", distinctProjects);

		List<Task> tasksByAssignee = taskRepository.findByAssigneeFirstName("vasile");
		LOG.info("Tasks assigned to vasile\n{}", tasksByAssignee);

		List<Task> overdueTasks = taskRepository.findByDueDateBeforeAndStatusEquals(LocalDate.now(), TaskStatus.TO_DO);
		LOG.info("Overdue Tasks:\n{}", overdueTasks);

		// save new entity
		Project newProject = new Project("NEW1", "new project", "new project description");
		LOG.info("Project id before persisting:\n{}", newProject.getId());
		projectRepository.save(newProject);
		LOG.info("Project id after persisting:\n{}", newProject.getId());
		// update entity (has id)
		newProject.setName("updated name");
		Set<Task> newProjectTasks = Set
				.of(new Task("task name", "task description", LocalDate.of(2026, 1, 1), TaskStatus.TO_DO, newProject));
		newProject.setTasks(newProjectTasks);
		newProject = projectRepository.save(newProject);
		LOG.info("Task after updating:\n{}", newProject.getTasks());

		// delete using reference
		/*
		 * Project p1 = projectRepository.findById(1L).get();
		 * projectRepository.delete(p1);
		 * 
		 * // delete using id projectRepository.deleteById(2L);
		 * 
		 * Iterable<Project> projectsToDelete =
		 * projectRepository.findAllById(List.of(3L, 5L));
		 * 
		 * // delete several projects projectRepository.deleteAll(projectsToDelete);
		 */
		// delete using custom query and with count
		// Long deleteCount = projectRepository.deleteByNameContaining("Project 2");

		// LOG.info("Number of removed projects:\n{}", deleteCount);

		// Using @Query
		List<Project> myProjects = projectRepository.findWithNameAndDescription();
		LOG.info("Project 3:\n{}", myProjects);

		Optional<String> projectName = projectRepository.findNameByCode();
		projectName.ifPresent(p -> LOG.info("Project Name:\n{}", p));

		Project project = projectRepository.findSingleProject();
		LOG.info("Single Project:\n{}", project);

		// Using @Query with Parameters
		List<Project> projectList1 = projectRepository.findWithNameAndDescriptionPositionalBind("Project 3",
				"About Project 3");
		LOG.info("find Project 3 using positional parameters:\n{}", projectList1);

		List<Project> projectList2 = projectRepository.findWithNameAndDescriptionNamedBind("About Project 3",
				"Project 3");
		LOG.info("find Project 3 using named parameters:\n{}", projectList2);

		// Named Queries
		List<Project> projectsWithIdGt1 = entityManager
				.createNamedQuery("namedQueryProjectsWithIdGreaterThan", Project.class).setParameter("id", 1L)
				.getResultList();
		LOG.info("Find Projects with Id greater than 1 using EntityManager:\n{}", projectsWithIdGt1);
		List<Project> projectsWithShortDescription = projectRepository.findProjectsWithDescriptionShorterThan(16);
		LOG.info("Find Projects with description shorter than 16:\n{}", projectsWithShortDescription);

	}

}
