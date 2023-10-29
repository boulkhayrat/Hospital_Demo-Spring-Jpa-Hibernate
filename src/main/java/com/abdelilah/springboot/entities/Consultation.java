package com.abdelilah.springboot.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Consultation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateConsultation;
	private String rapport;
	
	@OneToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private RendezVous rendezVous;




}
