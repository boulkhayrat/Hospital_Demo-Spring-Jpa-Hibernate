package com.abdelilah.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdelilah.springboot.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
	
	

}
