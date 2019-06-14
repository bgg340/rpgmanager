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



//the main controller of the program
@Controller
public class CharacterController {
		@Autowired
		private CharacterRepository characterRepository;
		
		@Autowired
		private GroupRepository groupRepository;
		
		@Autowired
		private PlayerRepository playerRepository;
		
		
	//the character list. gets all required info from said repositories.
		@RequestMapping("/charlist")
		public String characterList(Model model) {
			model.addAttribute("characters", characterRepository.findAll());
			model.addAttribute("groups", characterRepository.findAll());
			return "charlist";
		}
		
	//same as above but for a list of players.	
		@RequestMapping("/playerlist")
		public String playerrList(Model model) {
			model.addAttribute("players", playerRepository.findAll());
			model.addAttribute("characters", characterRepository.findAll());
			model.addAttribute("groups", characterRepository.findAll());
			return "playerlist";
		}
		
		
		//for viewing one character at a time. uses pathVariable to find right one through id
		@RequestMapping("/charactersheet/{id}")
		public String characterSheet(@PathVariable("id") long id, Model model) {
			model.addAttribute("characters", characterRepository.findAll());
			model.addAttribute("groups", characterRepository.findAll());
			return "charactersheet";
		}
		
		//for creating new characters
		@RequestMapping(value = "/add")
		public String addCharacter(Model model) {
			model.addAttribute("character", new Character());
			model.addAttribute("groups", groupRepository.findAll());
			return "addcharacter";
		}
		
		//same as add, but this was a test for making a separate one for non-admins. this might be useless?
				@RequestMapping(value = "/useradd")
				public String addCharacterAsUser(Model model) {
					model.addAttribute("character", new Character());
					model.addAttribute("groups", groupRepository.findAll());
					return "addcharacter";
				}
		
		
		//edit
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editCharacter(@PathVariable("id") long id, Model model) {

			model.addAttribute("character", characterRepository.findById(id));
			model.addAttribute("groups", groupRepository.findAll());
			return "editchar";
		}
		
		
		//for saving new characters or edits to old ones
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Character character) {
			characterRepository.save(character);
			return "redirect:charlist";
		}
		
		//for saving player edits.
		@RequestMapping(value = "/saveplr", method = RequestMethod.POST)
		public String saveplayer(Player player) {
			playerRepository.save(player);
			
			return "redirect:playerlist";
		}
		
		//delete a character
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteCharacter(@PathVariable("id") long id, Model model) {
			characterRepository.deleteById(id);
			return "redirect:../charlist";
		}
		
		//delete a user/player.
		@RequestMapping(value = "/deleteplayer/{id}", method = RequestMethod.GET)
		public String deletePlayer(@PathVariable("id") long id, Model model) {
			playerRepository.deleteById(id);
			return "redirect:../playerlist";
		}
		
		
		//for editing players. only meant for changing their characters, but you could do stupid stuff with this...
		@RequestMapping(value = "/editplr/{id}", method = RequestMethod.GET)
		public String editPlayer(@PathVariable("id") long id, Model model) {
			
			model.addAttribute("player", playerRepository.findById(id));
			model.addAttribute("characters", characterRepository.findAll());
			return "editplayer";
		}
		
		
		
		
        //restful stuff.
		
		//you can see all the characters and more here. Should probably be locked somehow...
		@RequestMapping(value = "/characters", method=RequestMethod.GET)
		@ResponseBody
		public List<Character> characterListRest() {
			return (List<Character>) characterRepository.findAll();
		}
		
		//view one character based on id
		@RequestMapping(value = "/character/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Optional<Character> findCharacterRest(@PathVariable("id") Long id){
			return characterRepository.findById(id);
		}
		
		//rest delete tool
		@RequestMapping(value = "/character/{id}", method=RequestMethod.DELETE)
		@ResponseBody
		public String deleteRest(@PathVariable("id") Long id){
			
			String characterName = characterRepository.findById(id).get().getName();
			
			characterRepository.deleteById(id);
			
			return characterName + " deleted";
		}
		
		
		//login request
		 @RequestMapping(value="/login")
		  public String login() {
		    return "login";
		  }
		
}
