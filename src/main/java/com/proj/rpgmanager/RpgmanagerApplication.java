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
		
	
	//using commandlinerunner to create filler content for our in-memory database
	
	@Bean
	public CommandLineRunner demo(CharacterRepository characterRepository, GroupRepository groupRepository, PlayerRepository playerRepository) {
	return (args) -> {
		
		/*group first, then characters and last the players, aka. users. 
		 This is due to the structure of our database tables.
		Can't insert groups for characters if they don't exist.		
		*/
		
		Group group1 = new Group("Main party");
		Group group2 = new Group("Sidequesters");
		Group group3 = new Group("Unassigned");
		
		groupRepository.save(group1);
		groupRepository.save(group2);
		groupRepository.save(group3);
		
		Character char1 = new Character("Henk", "fighter", "human", "todellinen perusjamppa", 1, 12, 13, 14, 10, 12, 13, 12, 10, "this man has no backstory because he is dull and basic", group1);
		Character char2 = new Character("Vold", "Monk", "human", "turhake", 1, 12, 13, 14, 10, 12, 13, 12, 10, "This man used to be a baker but for some reason he wanted to become a hero. Now he kicks ass and stuff", group1);
		Character char3 = new Character("Yrgl", "Wizard", "dwarf", "ahistunu jäbä", 1, 12, 13, 14, 10, 12, 13, 12, 10, "lorem ipsum massiivinen backstory, synkkä menneisyys liiba laaba lässyn lää. orpo ja rampa ja kaikki päin persettä", group2);	
		
		characterRepository.save(char1);
		characterRepository.save(char2);
		characterRepository.save(char3);
		

		//passwords hashed. users have "user" and admin has "admin".
		Player p1 = new Player("harri", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", char1);
		Player p2 = new Player("joonas", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", char2);
		Player p3 = new Player("henna", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", char3);
		Player p4 = new Player("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", char1);
		
		playerRepository.save(p1);
		playerRepository.save(p2);
		playerRepository.save(p3);
		playerRepository.save(p4);
		
	};
	}
		
}
