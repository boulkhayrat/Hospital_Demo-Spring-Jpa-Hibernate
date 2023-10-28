package com.abdelilah.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.exception.ResourceNotFoundException;
import com.abdelilah.springboot.repositories.ConsultationRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ConsultationServiceImp implements IConsultationService {

	
	private ConsultationRepository consultationRepository;
	
	public ConsultationServiceImp(ConsultationRepository consultationRepository) {
		
		this.consultationRepository = consultationRepository;
	}

	@Override
	public Consultation saveConsultation(Consultation consultation) {
		return consultationRepository.save(consultation);
	}

	@Override
	public List<Consultation> findAllConsultations() {
		return consultationRepository.findAll();
	}

	@Override
	public Consultation updateConsultation(Consultation consultation) {
		Optional<Consultation> consultationDB = consultationRepository.findById(consultation.getId());
		if(consultationDB.isPresent()) {
			Consultation consultationUpdate = consultationDB.get();
			consultationUpdate.setDateConsultation(consultation.getDateConsultation());
			consultationUpdate.setRapport(consultation.getRapport());
			consultationUpdate.setRendezVous(consultation.getRendezVous());
			
			return consultationUpdate;
		}
		else throw new ResourceNotFoundException("record not found with id : "+consultation.getId());
	}

	@Override
	public Consultation findConsultationById(Long id) {
		Optional<Consultation> consultationDB = consultationRepository.findById(id);
		if(consultationDB.isPresent()) {
			return consultationDB.get();
		}
		else throw new ResourceNotFoundException("record not found with id: "+id);
	}

	@Override
	public void deleteConsultation(Long id) {
		Optional<Consultation> consultationDB = consultationRepository.findById(id);
		if(consultationDB.isPresent()) {
			consultationRepository.delete(consultationDB.get());
			
		}
		else throw new ResourceNotFoundException("record not found with id : "+id);
	}
	
}
