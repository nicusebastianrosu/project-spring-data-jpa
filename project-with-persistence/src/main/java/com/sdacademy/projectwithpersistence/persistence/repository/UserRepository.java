package com.sdacademy.projectwithpersistence.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sdacademy.projectwithpersistence.persistence.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
