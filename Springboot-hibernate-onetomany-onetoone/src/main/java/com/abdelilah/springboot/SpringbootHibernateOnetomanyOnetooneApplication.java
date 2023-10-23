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
import com.abdelilah.springboot.repositories.MedecinRepository;
import com.abdelilah.springboot.repositories.PatientRepository;
import com.abdelilah.springboot.repositories.RendezVousRepository;
import com.abdelilah.springboot.service.IHospitalService;

@SpringBootApplication
public class SpringbootHibernateOnetomanyOnetooneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOnetomanyOnetooneApplication.class, args);
		
		
	}
	
	
	/**
	 * @param patientRepository
	 * @param medecinRepository
	 * @return
	 */
	@Bean
	CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository) {
		return args -> {
			Stream.of("Mohamed","Ahmed","Mostafa")
				.forEach(name -> {
					Patient patient = new Patient();
					patient.setNom(name);
					patient.setDateNaissance(new Date());
					patient.setMalade(false);
					patientRepository.save(patient);
				});
			Stream.of("Khalid","Abdelilah","Saiid")
				.forEach(name -> {
					Medecin medecin = new Medecin();
					medecin.setNom(name);
					medecin.setEmail(name + "gmail.com");
					medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
					medecinRepository.save(medecin);
			});
			
			Patient patient = patientRepository.findById(1L).orElse(null);
		
			
			Medecin medecin = medecinRepository.findById(3L).orElse(null);
		
			
			
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			
			hospitalService.saveRdv(rendezVous);
			
			RendezVous rdv = rendezVousRepository.findAll().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rdv);
			consultation.setRapport("rapport de la consultation ..");
			
			hospitalService.saveConsultation(consultation);
			
			};
			
			
		};
}


