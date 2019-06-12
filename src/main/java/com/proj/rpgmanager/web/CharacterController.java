package com.proj.rpgmanager.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.rpgmanager.domain.Character;
import com.proj.rpgmanager.domain.CharacterRepository;
import com.proj.rpgmanager.domain.GroupRepository;



@Controller
public class CharacterController {
		@Autowired
		private CharacterRepository characterRepository;
		
		@Autowired
		private GroupRepository groupRepository;
		
		
	
		@RequestMapping("/charlist")
		public String characterList(Model model) {
			model.addAttribute("characters", characterRepository.findAll());
			return "charlist";
		}
		
		@RequestMapping(value = "/add")
		public String addCharacter(Model model) {
			model.addAttribute("character", new Character());
			model.addAttribute("groups", characterRepository.findAll());
			return "addcharacter";
		}
		
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Character character) {
			characterRepository.save(character);
			return "redirect:charlist";
		}
		
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteCharacter(@PathVariable("id") long id, Model model) {
			characterRepository.deleteById(id);
			return "redirect:../charlist";
		}
		
		//edit
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editCharacter(@PathVariable("id") long id, Model model) {

			model.addAttribute("character", characterRepository.findById(id));
			model.addAttribute("groups", groupRepository.findAll());
			return "editchar";
		}
		
		
		
		
		

		
		@RequestMapping(value = "/characters", method=RequestMethod.GET)
		@ResponseBody
		public List<Character> characterListRest() {
			return (List<Character>) characterRepository.findAll();
			
		}


		@RequestMapping(value = "/character/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Optional<Character> findCharacterRest(@PathVariable("id") Long id){
			return characterRepository.findById(id);
		}
		
		@RequestMapping(value = "/character/{id}", method=RequestMethod.DELETE)
		@ResponseBody
		public String deleteRest(@PathVariable("id") Long id){
			
			String characterName = characterRepository.findById(id).get().getName();
			
			characterRepository.deleteById(id);
			
			return characterName + " deleted";
		}
		
		 @RequestMapping(value="/login")
		  public String login() {
		    return "login";
		  }
		
}
