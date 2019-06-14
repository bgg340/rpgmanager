package com.proj.rpgmanager.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* the group entity. apparently Group is a bad word in the database,
so it had to be altered to charGroup. */
@Entity
@Table(name="charGroup")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long groupId;
	private String groupName;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "group")
	private List<Character> characters;

	//constructors
	public Group(String groupName) {
		super();
		this.groupName = groupName;
	}
	
	
	public Group() {
		
	}

    //get and set
	public long getGroupId() {
		return groupId;
	}


	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public List<Character> getCharacters() {
		return characters;
	}


	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
}
