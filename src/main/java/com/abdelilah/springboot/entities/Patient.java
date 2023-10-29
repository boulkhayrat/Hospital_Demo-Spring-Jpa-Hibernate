package com.abdelilah.springboot.entities;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private boolean malade;
	
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private Collection<RendezVous> rendezVous;



}
