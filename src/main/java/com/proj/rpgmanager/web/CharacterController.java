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
import com.proj.rpgmanager.domain.Player;
import com.proj.rpgmanager.domain.PlayerRepository;




@Controller
public class CharacterController {
		@Autowired
		private CharacterRepository characterRepository;
		
		@Autowired
		private GroupRepository groupRepository;
		
		@Autowired
		private PlayerRepository playerRepository;
		
		
	
		@RequestMapping("/charlist")
		public String characterList(Model model) {
			model.addAttribute("characters", characterRepository.findAll());
			model.addAttribute("groups", characterRepository.findAll());
			return "charlist";
		}
		
		@RequestMapping("/playerlist")
		public String playerrList(Model model) {
			model.addAttribute("players", playerRepository.findAll());
			model.addAttribute("characters", characterRepository.findAll());
			model.addAttribute("groups", characterRepository.findAll());
			return "playerlist";
		}
		
		
		
		@RequestMapping("/charactersheet/{id}")
		public String characterSheet(@PathVariable("id") long id, Model model) {
			model.addAttribute("characters", characterRepository.findAll());
			model.addAttribute("groups", characterRepository.findAll());
			return "charactersheet";
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
		
		@RequestMapping(value = "/saveplr", method = RequestMethod.POST)
		public String saveplayer(Player player) {
			playerRepository.save(player);
			
			return "redirect:playerlist";
		}
		
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteCharacter(@PathVariable("id") long id, Model model) {
			characterRepository.deleteById(id);
			return "redirect:../charlist";
		}
		
		@RequestMapping(value = "/deleteplayer/{id}", method = RequestMethod.GET)
		public String deletePlayer(@PathVariable("id") long id, Model model) {
			playerRepository.deleteById(id);
			return "redirect:../playerlist";
		}
		
		//edit
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editCharacter(@PathVariable("id") long id, Model model) {

			model.addAttribute("character", characterRepository.findById(id));
			model.addAttribute("groups", groupRepository.findAll());
			return "editchar";
		}
		
		@RequestMapping(value = "/editplr/{id}", method = RequestMethod.GET)
		public String editPlayer(@PathVariable("id") long id, Model model) {
			
			model.addAttribute("player", playerRepository.findById(id));
			model.addAttribute("characters", characterRepository.findAll());
			return "editplayer";
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
