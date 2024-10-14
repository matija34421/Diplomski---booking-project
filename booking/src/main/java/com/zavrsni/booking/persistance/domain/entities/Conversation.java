package com.zavrsni.booking.domain.entities;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.*;
@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@OneToMany(mappedBy = "conversation")
	private List<Messages> messages;
	
	@ManyToMany
	@JoinTable(name = "participants",
				joinColumns = @JoinColumn(name="conversation_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> users;
	
	public Conversation() {}
	
	public Conversation(int Id) {
		this.Id = Id;
		this.messages = Collections.emptyList();
	}
	
	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id=id;
	}
	
	public List<Messages> getMessages(){
		return messages;
	}
	public void setMessages(Messages message) {
		messages.add(message);
	}
	
	public List<User> getUsers(){
		return this.users;
	}
	
	public void setUsers(User user) {
		this.users.add(user);
	}
}
