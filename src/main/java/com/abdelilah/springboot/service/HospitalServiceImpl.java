package com.abdelilah.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.repositories.ConsultationRepository;
import com.abdelilah.springboot.repositories.MedecinRepository;
import com.abdelilah.springboot.repositories.PatientRepository;
import com.abdelilah.springboot.repositories.RendezVousRepository;

import jakarta.transaction.Transactional;




@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

	private PatientRepository patientRepository;
	private MedecinRepository medecinRepository;
	private RendezVousRepository rendezVousRepository;
	private ConsultationRepository consultationRepository;
	
	public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
		
		this.patientRepository = patientRepository;
		this.medecinRepository = medecinRepository;
		this.rendezVousRepository = rendezVousRepository;
		this.consultationRepository = consultationRepository;
	}

	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	@Override
	public Medecin saveMedecin(Medecin medecin) {
		// TODO Auto-generated method stub
		return medecinRepository.save(medecin);
	}

	@Override
	public RendezVous saveRdv(RendezVous rendezVous) {
		rendezVous.setId(UUID.randomUUID().toString());
		return rendezVousRepository.save(rendezVous);
	}

	@Override
	public Consultation saveConsultation(Consultation consultation) {
		// TODO Auto-generated method stub
		return consultationRepository.save(consultation);
	}

	@Override
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
		
	}

}
