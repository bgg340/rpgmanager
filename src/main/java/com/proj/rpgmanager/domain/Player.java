package com.proj.rpgmanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerId", nullable = false, updatable = false)
    private Long playerId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne
	@JsonIgnore
	@JoinColumn(name="id")
	private Character character;
    
    
    
    public Player() {
    	
    }
    
	public Player(String username, String passwordHash, String role, Character character) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.character = character;
	}

	
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
    
	
	
    
}
