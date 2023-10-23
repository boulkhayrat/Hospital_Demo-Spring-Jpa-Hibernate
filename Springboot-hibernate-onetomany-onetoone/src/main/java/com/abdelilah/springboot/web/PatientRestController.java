package com.abdelilah.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.repositories.PatientRepository;

@RestController
public class PatientRestController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/patients")
	public List<Patient> patientList(){
		return patientRepository.findAll();
	}
	

}
