package com.proj.rpgmanager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface GroupRepository extends CrudRepository<Group, Long> {

	
	List<Group> findByGroupName(String groupName);
	
	
}