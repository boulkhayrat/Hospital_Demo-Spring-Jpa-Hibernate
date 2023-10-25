package com.abdelilah.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.service.IHospitalService;

@RestController
public class PatientRestController {
	
	
	
	
	@Autowired
	private  IHospitalService hospitalService;
	
	
	@GetMapping("/patients")
	public List<Patient> patientList(){
		return hospitalService.findAllPatients();
	}
	
    @PostMapping("/patient")
    public Patient savePatient(@RequestBody Patient patient) {
        return hospitalService.savePatient(patient);
    }

    @PostMapping("/medecin")
    public Medecin saveMedecin(@RequestBody Medecin medecin) {
        return hospitalService.saveMedecin(medecin);
    }

    @PostMapping("/rendezvous")
    public RendezVous saveRendezVous(@RequestBody RendezVous rendezVous) {
        return hospitalService.saveRdv(rendezVous);
    }

    @PostMapping("/consultation")
    public Consultation saveConsultation(@RequestBody Consultation consultation) {
        return hospitalService.saveConsultation(consultation);
    }

}
