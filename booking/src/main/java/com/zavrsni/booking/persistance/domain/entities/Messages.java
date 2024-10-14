package com.zavrsni.booking.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "message")
	private String Message;
	
	@ManyToOne
	@JoinColumn(name = "user_id" , referencedColumnName = "Id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "conversation_id" , referencedColumnName = "Id")
	private Conversation conversation;
	
	public Messages() {}
	
	public Messages(int Id,Conversation conversation,User user,String Message) {
		this.Id=Id;
		this.user=user;
		this.conversation=conversation;
		this.Message=Message;
	}
	
	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id=id;
	}
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user=user;
	}
	
	public Conversation getConversation() {
		return this.conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation=conversation;
	}
	
	public String getMessage() {
		return this.Message;
	}
	public void setMessage(String message) {
		this.Message=message;
	}
}
