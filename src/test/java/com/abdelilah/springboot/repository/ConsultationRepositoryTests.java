package com.abdelilah.springboot.repository;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.repositories.ConsultationRepository;
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
public class ConsultationRepositoryTests {

	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Test 
	public void ConsultationRepository_SaveAll_ReturnSavedConsultation() {


		Consultation consultation  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();


		Consultation savedConsultation = consultationRepository.save(consultation);
		
		
		Assertions.assertThat(savedConsultation).isNotNull();
		Assertions.assertThat(savedConsultation.getId()).isGreaterThan(0);
		
		
	}

	@Test
	public void ConsultationRepository_GetAll_ReturnMoreThanOneConsultation(){

		Consultation consultation  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();
		Consultation consultation1  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();
		Consultation consultation2  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();

		consultationRepository.save(consultation);
		consultationRepository.save(consultation1);
		consultationRepository.save(consultation2);

		List<Consultation> consultationList = consultationRepository.findAll();

		Assertions.assertThat(consultationList).isNotNull();
		Assertions.assertThat(consultationList.size()).isEqualTo(3);


	}

	@Test
	public void ConsultationRepository_FindById_ReturnConsultation() {


		Consultation consultation  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();

		consultationRepository.save(consultation);
		Consultation consultation1 = consultationRepository.findById(consultation.getId()).get();


		Assertions.assertThat(consultation1).isNotNull();



	}

	@Test
	public void ConsultationRepository_UpdateConsultation_ReturnConsultation() {


		Consultation consultation  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();

		consultationRepository.save(consultation);
		Consultation consultation1 = consultationRepository.findById(consultation.getId()).get();


		consultation1.setRapport("Consultation's Report .....");

		Consultation updatedConsultation = consultationRepository.save(consultation1);





		Assertions.assertThat(updatedConsultation.getRapport()).isEqualTo("Consultation's Report .....");



	}

	@Test
	public void ConsultationRepository_DeleteConsultation_ReturnEmpty(){
		Consultation consultation  = Consultation.builder()
				.rapport("rapport pour la consultation ...")
				.dateConsultation(new Date())
				.build();
		consultationRepository.save(consultation);

		consultationRepository.delete(consultation);
		Optional<Consultation> consultation1 = consultationRepository.findById(consultation.getId());

		Assertions.assertThat(consultation1).isEmpty();


	}
}
