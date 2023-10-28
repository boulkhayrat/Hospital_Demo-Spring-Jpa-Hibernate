package com.abdelilah.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.exception.ResourceNotFoundException;
import com.abdelilah.springboot.repositories.RendezVousRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class RendezVousServiceImp implements IRendezVousService{

	
	private RendezVousRepository rendezVousRepository;
	
	
	public RendezVousServiceImp(RendezVousRepository rendezVousRepository) {
		
		this.rendezVousRepository = rendezVousRepository;
	}

	@Override
	public RendezVous saveRendezVous(RendezVous rendezVous) {
		return rendezVousRepository.save(rendezVous);
	}

	@Override
	public List<RendezVous> findAllRendezVous() {
		return rendezVousRepository.findAll();
	}

	@Override
	public RendezVous updateRendezVous(RendezVous rendezVous) {
		Optional<RendezVous> rendezVousDB = rendezVousRepository.findById(rendezVous.getId());
		if(rendezVousDB.isPresent()) {
			RendezVous rendezVousUpdate = rendezVousDB.get();
			rendezVousUpdate.setConsultation(rendezVous.getConsultation());
			rendezVousUpdate.setDate(rendezVous.getDate());
			rendezVousUpdate.setMedecin(rendezVous.getMedecin());
			rendezVousUpdate.setPatient(rendezVous.getPatient());
			rendezVousUpdate.setStatus(rendezVous.getStatus());
			
			return rendezVousUpdate;
		}
		else throw new ResourceNotFoundException("record not found with id : "+rendezVous.getId());
	}

	@Override
	public RendezVous findRendezVousById(Long id) {
		Optional<RendezVous> rendezVousDB = rendezVousRepository.findById(id);
		
		if(rendezVousDB.isPresent()) {
			return rendezVousDB.get();
			
		}
		else throw new ResourceNotFoundException("record not found with id : "+id);
	}

	@Override
	public void deleteRendezVous(Long id) {
		Optional<RendezVous> rendezVousDB = rendezVousRepository.findById(id);
		if(rendezVousDB.isPresent()) {
			rendezVousRepository.delete(rendezVousDB.get());
		}
		else throw new ResourceNotFoundException("record not found with id :"+id);
		
	}

}
