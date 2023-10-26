package com.abdelilah.springboot.service;

import java.util.List;
import java.util.Optional;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.exception.ResourceNotFoundException;
import com.abdelilah.springboot.repositories.PatientRepository;

public class PatientServiceImp implements IPatientService{

	private PatientRepository patientRepository;
	
	
	
	
	public PatientServiceImp(PatientRepository patientRepository) {
		
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient updatePatient(Patient patient) {
		Optional<Patient> patientDB = patientRepository.findById(patient.getId());
		if(patientDB.isPresent()) {
			Patient patientUpdate = patientDB.get();
			patientUpdate.setDateNaissance(patient.getDateNaissance());
			patientUpdate.setMalade(patient.isMalade());
			patientUpdate.setNom(patient.getNom());
			patientUpdate.setRendezVous(patient.getRendezVous());
			
			return patientUpdate;
		}
			
		else {
			throw new ResourceNotFoundException("record not found with id " + patient.getId());
		}
	}

	@Override
	public Patient findPatientById(Long id) {
		Optional<Patient> patientDB = patientRepository.findById(id);		
		if(patientDB.isPresent()) {
			return patientDB.get();
		}
		else {
			throw new ResourceNotFoundException("record not found with id " + id);
		}
		
	}

	@Override
	public void deletePatient(Long id) {
		Optional<Patient> patientDB = patientRepository.findById(id);		
		if(patientDB.isPresent()) {
			patientRepository.delete(patientDB.get());
		}
		else {
			throw new ResourceNotFoundException("record not found with id " + id);
		}
		
	}

}
