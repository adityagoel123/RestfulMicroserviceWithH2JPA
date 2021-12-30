package com.adityaebs.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskForUserRepository extends JpaRepository<Task, Long>{
	List<Task> findByUsername(String username);
}