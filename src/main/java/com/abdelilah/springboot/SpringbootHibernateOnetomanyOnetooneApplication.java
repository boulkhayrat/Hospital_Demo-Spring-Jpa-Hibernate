package com.abdelilah.springboot;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.entities.StatusRDV;
import com.abdelilah.springboot.service.IConsultationService;
import com.abdelilah.springboot.service.IMedecinService;
import com.abdelilah.springboot.service.IPatientService;
import com.abdelilah.springboot.service.IRendezVousService;

@SpringBootApplication
public class SpringbootHibernateOnetomanyOnetooneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOnetomanyOnetooneApplication.class, args);
		
		
	}
	
	
	@Bean
	CommandLineRunner start(IPatientService patientService, IMedecinService medecinService, IRendezVousService rendezVousService, IConsultationService consultationService) {
		return args -> {
			Stream.of("Mohamed","Ahmed","Mostafa")
				.forEach(name -> {
					Patient patient = new Patient();
					patient.setNom(name);
					patient.setDateNaissance(new Date());
					patient.setMalade(false);
					patientService.savePatient(patient);
				});
			Stream.of("Khalid","Abdelilah","Saiid")
				.forEach(name -> {
					Medecin medecin = new Medecin();
					medecin.setNom(name);
					medecin.setEmail(name + "gmail.com");
					medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
					medecinService.saveMedecin(medecin);
			});
			
			Patient patient = patientService.findPatientById(1L);
		
			
			Medecin medecin = medecinService.findMedecinById(1L);
		
			
			
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			
			rendezVousService.saveRendezVous(rendezVous);
			
			RendezVous rdv = rendezVousService.findAllRendezVous().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rdv);
			consultation.setRapport("rapport de la consultation ..");
			
			consultationService.saveConsultation(consultation);
			
			};
			
			
		};
}


