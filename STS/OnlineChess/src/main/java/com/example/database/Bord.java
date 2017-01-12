package com.example.database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bord {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<SchaakVeld> velden;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<SchaakVeld> getVelden() {
		return velden;
	}
	public void setVelden(List<SchaakVeld> velden) {
		this.velden = velden;
	}		
	
}