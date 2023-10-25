package com.abdelilah.springboot.service;

import java.util.List;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;

public interface IHospitalService {

	Patient savePatient(Patient patient);
	List<Patient> findAllPatients();
	Medecin saveMedecin(Medecin medecin);
	RendezVous saveRdv(RendezVous rendexVous);
	Consultation saveConsultation(Consultation consultation);
	
	
	
}
