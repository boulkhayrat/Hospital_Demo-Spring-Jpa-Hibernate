package com.abdelilah.springboot.repository;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.entities.StatusRDV;
import com.abdelilah.springboot.repositories.RendezVousRepository;
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
public class RendezVousRepositoryTests {

	
	@Autowired
	private RendezVousRepository rendezVousRepository;
	
	@Test
	public void RendezVousRepository_SaveRendezVous_ReturnSavedRendezVous() {
		
		
		RendezVous rendezVous = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.DONE)
				.build();



		RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
		
		
		Assertions.assertThat(savedRendezVous).isNotNull();
		Assertions.assertThat(savedRendezVous.getId()).isGreaterThan(0);
		
		
	}

	@Test
	public void RendezVousRepository_GetAll_ReturnMoreThanOneRendezVous(){

		RendezVous rendezVous = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.DONE)
				.build();

		RendezVous rendezVous1 = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.PENDING)
				.build();

		RendezVous rendezVous2 = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.CANCELED)
				.build();


		rendezVousRepository.save(rendezVous);
		rendezVousRepository.save(rendezVous1);
		rendezVousRepository.save(rendezVous2);

		List<RendezVous> rendezVousList = rendezVousRepository.findAll();

		Assertions.assertThat(rendezVousList).isNotNull();
		Assertions.assertThat(rendezVousList.size()).isEqualTo(3);


	}

	@Test
	public void RendezVousRepository_FindById_ReturnRendezVous() {


		RendezVous rendezVous = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.DONE)
				.build();

		rendezVousRepository.save(rendezVous);
		RendezVous rendezVous1 = rendezVousRepository.findById(rendezVous.getId()).get();


		Assertions.assertThat(rendezVous1).isNotNull();



	}

	@Test
	public void RendezVousRepository_UpdateRendezVous_ReturnRendezVous() {


		RendezVous rendezVous = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.DONE)
				.build();

		rendezVousRepository.save(rendezVous);
		RendezVous rendezVous1 = rendezVousRepository.findById(rendezVous.getId()).get();

		rendezVous1.setStatus(StatusRDV.CANCELED);


		RendezVous updatedRendezVous = rendezVousRepository.save(rendezVous1);




		Assertions.assertThat(updatedRendezVous.getStatus()).isEqualTo(StatusRDV.CANCELED);




	}

	@Test
	public void RendezVousRepository_DeleteRendezVous_ReturnEmpty(){
		RendezVous rendezVous = RendezVous.builder()
				.date(new Date())
				.status(StatusRDV.DONE)
				.build();
		rendezVousRepository.save(rendezVous);

		rendezVousRepository.delete(rendezVous);
		Optional<RendezVous> rendezVous1 = rendezVousRepository.findById(rendezVous.getId());

		Assertions.assertThat(rendezVous1).isEmpty();


	}
}
