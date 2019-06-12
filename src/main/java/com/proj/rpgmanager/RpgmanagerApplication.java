package com.proj.rpgmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.proj.rpgmanager.domain.Character;
import com.proj.rpgmanager.domain.CharacterRepository;
import com.proj.rpgmanager.domain.Group;
import com.proj.rpgmanager.domain.GroupRepository;
import com.proj.rpgmanager.domain.Player;
import com.proj.rpgmanager.domain.PlayerRepository;

@SpringBootApplication
public class RpgmanagerApplication {
	private static final Logger log = LoggerFactory.getLogger(RpgmanagerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RpgmanagerApplication.class, args);
	}
		
	@Bean
	public CommandLineRunner demo(CharacterRepository characterRepository, GroupRepository groupRepository, PlayerRepository playerRepository) {
	return (args) -> {
		
		Group group1 = new Group("Main party");
		Group group2 = new Group("Sidequesters");
		
		groupRepository.save(group1);
		groupRepository.save(group2);
		
		Character char1 = new Character("Jamppa", "fighter", "human", "todellinen perusjamppa", 1, 12, 13, 14, 10, 12, 13, 12, 10, group1);
		Character char2 = new Character("Timppa", "Monk", "human", "turhake", 1, 12, 13, 14, 10, 12, 13, 12, 10, group1);
		Character char3 = new Character("Yrgl", "Wizard", "dwarf", "ahistunu jäbä", 1, 12, 13, 14, 10, 12, 13, 12, 10, group2);	
		
		characterRepository.save(char1);
		characterRepository.save(char2);
		characterRepository.save(char3);
		

		Player p1 = new Player("Jäbä", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", char1);
		Player p2 = new Player("Jubu", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", char2);
		Player p3 = new Player("Jebo", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", char3);
		Player p4 = new Player("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", char1);
		
		playerRepository.save(p1);
		playerRepository.save(p2);
		playerRepository.save(p3);
		playerRepository.save(p4);
		
	};
	}
		
}
