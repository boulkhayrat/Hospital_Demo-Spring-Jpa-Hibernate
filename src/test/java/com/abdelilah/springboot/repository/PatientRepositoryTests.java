package com.abdelilah.springboot.repository;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.repositories.PatientRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PatientRepositoryTests {

	
	@Autowired
	private PatientRepository patientRepository;
	
	@Test 
	public void PatientRepository_SaveAll_ReturnSavedPatient() {
		
		
		Patient patient = Patient.builder()
				.nom("Abdelilah")
				.malade(false)
				.dateNaissance(new Date())
				.build();
		
		
		Patient savedPatient = patientRepository.save(patient);
		
		
		Assertions.assertThat(savedPatient).isNotNull();
		Assertions.assertThat(savedPatient.getId()).isGreaterThan(0);
		
		
	}
}
