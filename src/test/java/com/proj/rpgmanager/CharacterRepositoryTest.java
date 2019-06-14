package com.proj.rpgmanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.print.Book;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.proj.rpgmanager.domain.CharacterRepository;
import com.proj.rpgmanager.domain.Character;
import com.proj.rpgmanager.domain.Group;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CharacterRepositoryTest {

	@Autowired
    private CharacterRepository characterRepository;

    @Test
    public void findByNameShouldReturnCharacter() {
        List<Character> characters = characterRepository.findByName("Yrgl");
        assertThat(characters).hasSize(1);
        assertThat(characters.get(0).getRace()).isEqualTo("Dwarf");
    }
    
    @Test
    public void createNewCharacter() {
    	Character character = new Character("Valarxxx", "Ranger", "elf", "totally cool elf dude. no weaknesses.", 5, 15, 11, 17, 11, 14, 15, 13, 8, "This is a long and complicated backstory full of weird twists and plotholes", new Group("Backup team"));
    	characterRepository.save(character);
    	assertThat(character.getId()).isNotNull();
    }    
	
    
   
}
