package com.abdelilah.springboot.service;

import java.util.List;

import com.abdelilah.springboot.entities.Medecin;

public interface IMedecinService {
	Medecin savePatient(Medecin medecin);
	List<Medecin> findAllMedecins();
	Medecin updateMedecin(Medecin medecin);
	Medecin findMedecinById(Long id);
	void deleteMedecin(Long id);
}
