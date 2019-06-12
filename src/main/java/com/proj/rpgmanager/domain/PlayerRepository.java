package com.proj.rpgmanager.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;



public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	Player findByUsername(String playerName);
}