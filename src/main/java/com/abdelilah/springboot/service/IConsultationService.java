package com.abdelilah.springboot.service;

import java.util.List;

import com.abdelilah.springboot.entities.Consultation;

public interface IConsultationService {
	Consultation saveConsultation(Consultation consultation);
	List<Consultation> findAllConsultations();
	Consultation updateConsultation(Consultation consultation);
	Consultation findConsultationById(Long id);
	void deleteConsultation(Long id);
}
