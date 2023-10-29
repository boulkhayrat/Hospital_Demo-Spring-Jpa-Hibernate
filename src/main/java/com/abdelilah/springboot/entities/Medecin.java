package com.abdelilah.springboot.entities;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Medecin {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String email;
	private String specialite;
	
	@OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<RendezVous> rendesVous;



}
