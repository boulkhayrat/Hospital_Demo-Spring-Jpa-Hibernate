package com.abdelilah.springboot.service;

import java.util.List;

import com.abdelilah.springboot.entities.Patient;

public interface IPatientService {
	Patient savePatient(Patient patient);
	List<Patient> findAllPatients();
	Patient updatePatient(Patient patient);
	Patient findPatientById(Long id);
	void deletePatient(Long id);
	Boolean isNameValid(String name);
}
