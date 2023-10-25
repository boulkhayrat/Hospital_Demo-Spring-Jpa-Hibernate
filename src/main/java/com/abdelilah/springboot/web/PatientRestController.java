package com.abdelilah.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.service.IHospitalService;

@RestController
public class PatientRestController {
	
	
	
	
	@Autowired
	private  IHospitalService hospitalService;
	
	
	@GetMapping("/patients")
	public List<Patient> patientList(){
		return hospitalService.findAllPatients();
	}
	

}
