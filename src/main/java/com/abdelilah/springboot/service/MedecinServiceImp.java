package com.abdelilah.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.exception.ResourceNotFoundException;
import com.abdelilah.springboot.repositories.MedecinRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class MedecinServiceImp implements IMedecinService{

private MedecinRepository medecinRepository;
	
	
	
	
	public MedecinServiceImp(MedecinRepository medecinRepository) {
		
		this.medecinRepository = medecinRepository;
	}
	
	
	@Override
	public Medecin saveMedecin(Medecin medecin) {
		return medecinRepository.save(medecin);
	}

	@Override
	public List<Medecin> findAllMedecins() {
		return medecinRepository.findAll();
	}

	@Override
	public Medecin updateMedecin(Medecin medecin) {
		Optional<Medecin> medecinDB = medecinRepository.findById(medecin.getId());
		if(medecinDB.isPresent()) {
			Medecin medecinUpdate = medecinDB.get();
			medecinUpdate.setEmail(medecin.getEmail());
			medecinUpdate.setNom(medecin.getNom());
			medecinUpdate.setRendesVous(medecin.getRendesVous());
			medecinUpdate.setSpecialite(medecin.getSpecialite());
			
			
			return medecinUpdate;
		}
			
		else {
			throw new ResourceNotFoundException("record not found with id " + medecin.getId());
		}
	}

	@Override
	public Medecin findMedecinById(Long id) {
		Optional<Medecin> medecinDB = medecinRepository.findById(id);
		if(medecinDB.isPresent()) {
			return medecinDB.get();
			
		}
			
		else {
			throw new ResourceNotFoundException("record not found with id " + id);
		}
	}

	@Override
	public void deleteMedecin(Long id) {
		Optional<Medecin> medecinDB = medecinRepository.findById(id);
		if(medecinDB.isPresent()) {
			 medecinRepository.delete(medecinDB.get());;
			
		}
			
		else {
			throw new ResourceNotFoundException("record not found with id " + id);
		}
		
	}

}
