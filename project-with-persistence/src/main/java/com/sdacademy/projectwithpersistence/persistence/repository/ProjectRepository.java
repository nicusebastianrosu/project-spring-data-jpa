package com.sdacademy.projectwithpersistence.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sdacademy.projectwithpersistence.persistence.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	// we use methods with derived queries
	Optional<Project> findByCodeEquals(String code);

	int countByName(String name);

	// we use methods with more complex derived queries
	Iterable<Project> findByNameStartingWith(String name);

	Iterable<Project> findDistinctByTasksNameContaining(String taskName);

	// delete projects
	Iterable<Project> findByNameContaining(String name);

	// @Transactional
	// Long deleteByNameContaining(String name);

	// Using @Query
	@Query(value = "select p from Project as p where p.name='Project 3' and p.description='About Project 3'")
	List<Project> findWithNameAndDescription();

	@Query(value = "select p.name from Project as p where p.code='P1'")
	Optional<String> findNameByCode();

	@Query(nativeQuery = true, value = "select * from project limit 1")
	Project findSingleProject();

	// Using @Query with Parameters
	@Query("select p from Project p where p.name=?1 and p.description=?2")
	List<Project> findWithNameAndDescriptionPositionalBind(String name, String description);

	@Query("select p from Project p where p.name=:name and p.description=:description")
	List<Project> findWithNameAndDescriptionNamedBind(@Param("description") String description,
			@Param("name") String name);

	// Named Queries
	List<Project> findProjectsWithDescriptionShorterThan(@Param("length") int length);

}
