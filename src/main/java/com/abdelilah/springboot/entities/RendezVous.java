package com.abdelilah.springboot.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private StatusRDV status;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Patient patient;
	
	@ManyToOne
	private Medecin medecin;
	
	@OneToOne(mappedBy = "rendezVous")
	private Consultation consultation;


	
}