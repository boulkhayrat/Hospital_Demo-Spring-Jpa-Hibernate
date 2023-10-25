package com.abdelilah.springboot.service;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;

public interface IHospitalService {

	Patient savePatient(Patient patient);
	Medecin saveMedecin(Medecin medecin);
	RendezVous saveRdv(RendezVous rendexVous);
	Consultation saveConsultation(Consultation consultation);
	
	
	
}
