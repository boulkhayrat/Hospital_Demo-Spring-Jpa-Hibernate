package com.abdelilah.springboot.entities;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;
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
