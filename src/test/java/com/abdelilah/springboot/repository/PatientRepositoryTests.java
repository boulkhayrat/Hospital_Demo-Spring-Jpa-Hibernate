package com.abdelilah.springboot.repository;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.repositories.PatientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	@Test
	public void PatientRepository_GetAll_ReturnMoreThanOnePatient(){

		Patient patient = Patient.builder()
				.nom("Abdelilah")
				.malade(true)
				.dateNaissance(new Date())
				.build();
		Patient patient1 = Patient.builder()
				.nom("Anas")
				.malade(true)
				.dateNaissance(new Date())
				.build();
		Patient patient2 = Patient.builder()
				.nom("Mostafa")
				.malade(true)
				.dateNaissance(new Date())
				.build();

		patientRepository.save(patient);
		patientRepository.save(patient1);
		patientRepository.save(patient2);

		List<Patient> patientList = patientRepository.findAll();

		Assertions.assertThat(patientList).isNotNull();
		Assertions.assertThat(patientList.size()).isEqualTo(3);


	}

	@Test
	public void PatientRepository_FindById_ReturnPatient() {


		Patient patient = Patient.builder()
				.nom("Abdelilah")
				.malade(false)
				.dateNaissance(new Date())
				.build();

		patientRepository.save(patient);
		Patient patient1 = patientRepository.findById(patient.getId()).get();


		Assertions.assertThat(patient1).isNotNull();



	}

	@Test
	public void PatientRepository_UpdatePatient_ReturnPatient() {


		Patient patient = Patient.builder()
				.nom("Hamid")
				.malade(true)
				.dateNaissance(new Date())
				.build();

		patientRepository.save(patient);
		Patient patient1 = patientRepository.findById(patient.getId()).get();

		patient1.setNom("Abdel");
		patient1.setMalade(true);

		Patient updatedPatient = patientRepository.save(patient1);




		Assertions.assertThat(updatedPatient.getNom()).isNotNull();
		Assertions.assertThat(updatedPatient.isMalade()).isEqualTo(true);



	}

	@Test
	public void PatientRepository_DeletePatient_ReturnEmpty(){
		Patient patient = Patient.builder()
				.nom("Abdelilah")
				.malade(true)
				.dateNaissance(new Date())
				.build();
		patientRepository.save(patient);

		patientRepository.delete(patient);
		Optional<Patient> patient1 = patientRepository.findById(patient.getId());

		Assertions.assertThat(patient1).isEmpty();


	}
}
