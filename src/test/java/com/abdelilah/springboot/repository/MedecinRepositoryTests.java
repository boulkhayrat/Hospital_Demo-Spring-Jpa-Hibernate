package com.abdelilah.springboot.repository;

import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.repositories.MedecinRepository;
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
public class MedecinRepositoryTests {


	@Autowired
	private MedecinRepository medecinRepository;

	@Test
	public void MedecinRepository_SaveMedecin_ReturnSavedMedecin() {


		Medecin medecin = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();


		Medecin savedMedecin = medecinRepository.save(medecin);


		Assertions.assertThat(savedMedecin).isNotNull();
		Assertions.assertThat(savedMedecin.getId()).isGreaterThan(0);


	}

	@Test
	public void MedecinRepository_GetAll_ReturnMoreThanOneMedecin(){

		Medecin medecin = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();

		Medecin medecin1 = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();

		Medecin medecin2 = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();


		medecinRepository.save(medecin);
		medecinRepository.save(medecin1);
		medecinRepository.save(medecin2);

		List<Medecin> medecinList = medecinRepository.findAll();

		Assertions.assertThat(medecinList).isNotNull();
		Assertions.assertThat(medecinList.size()).isEqualTo(3);

	}

	@Test
	public void MedecinRepository_FindById_ReturnMedecin() {


		Medecin medecin = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();

		medecinRepository.save(medecin);
		Medecin medecin1 = medecinRepository.findById(medecin.getId()).get();


		Assertions.assertThat(medecin1).isNotNull();



	}

	@Test
	public void MedecinRepository_UpdateMedecin_ReturnMedecin() {


		Medecin medecin = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();

		medecinRepository.save(medecin);
		Medecin medecin1 = medecinRepository.findById(medecin.getId()).get();

		medecin1.setNom("Abdel");
		medecin1.setEmail("dfadfadsf@gmail.com");

		Medecin updatedMedecin = medecinRepository.save(medecin1);




		Assertions.assertThat(updatedMedecin.getNom()).isNotNull();
		Assertions.assertThat(updatedMedecin.getEmail()).isNotNull();



	}

	@Test
	public void MedecinRepository_DeleteMedecin_ReturnEmpty(){
		Medecin medecin = Medecin.builder()
				.nom("Khalid")
				.email("khalid@gmail.com")
				.specialite("Dentiste")
				.build();
		medecinRepository.save(medecin);

		medecinRepository.delete(medecin);
		Optional<Medecin> medecin1 = medecinRepository.findById(medecin.getId());

		Assertions.assertThat(medecin1).isEmpty();


	}
}
