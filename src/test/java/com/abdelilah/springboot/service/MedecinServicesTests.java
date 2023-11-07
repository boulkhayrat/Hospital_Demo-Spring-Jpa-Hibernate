package com.abdelilah.springboot.service;


import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.repositories.MedecinRepository;
import com.abdelilah.springboot.repositories.PatientRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedecinServicesTests {

    @Mock
    private MedecinRepository medecinRepository;

    @InjectMocks
    private MedecinServiceImp medecinService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePatient() {
        Medecin medecin = Medecin.builder()
                .nom("Khalid")
                .email("khalid@gmail.com")
                .specialite("Dentiste")
                .build();

        when(medecinRepository.save(medecin)).thenReturn(medecin);

        Medecin savedMedecin = medecinService.saveMedecin(medecin);

        verify(medecinRepository, times(1)).save(medecin);
        Assertions.assertEquals(medecin,savedMedecin);
    }
    @Test
    public void testFindAllPatients() {
        List<Medecin> medecins = new ArrayList<>();
        Medecin medecin = Medecin.builder()
                .nom("Khalid")
                .email("khalid@gmail.com")
                .specialite("Dentiste")
                .build();

        Medecin medecin1 = Medecin.builder()
                .nom("Khalid1")
                .email("khali1d@gmail.com")
                .specialite("Dentiste")
                .build();


        medecins.add(medecin);
        medecins.add(medecin1);
        medecinRepository.save(medecin);
        medecinRepository.save(medecin1);

        when(medecinRepository.findAll()).thenReturn(medecins);

        List<Medecin> result = medecinService.findAllMedecins();

        verify(medecinRepository, times(1)).findAll();
        Assertions.assertEquals(medecins, result);
    }

    @Test
    public void testUpdatePatient() {
        Medecin medecin = Medecin.builder()
                .nom("Khalid")
                .email("khalid@gmail.com")
                .specialite("Dentiste")
                .build();


        when(medecinRepository.findById(medecin.getId())).thenReturn(Optional.of(medecin));


        Medecin medecin1 = Medecin.builder()
                .nom("Anas")
                .email("Anas@gmail.com")
                .specialite("Dentiste")
                .build();


        Medecin result = medecinService.updateMedecin(medecin1);


        verify(medecinRepository, times(1)).findById(medecin.getId());

        Assertions.assertEquals(medecin1, result);
    }

    @Test
    public void testFindPatientById() {
        Medecin medecin = Medecin.builder()
                .nom("Khalid")
                .email("khalid@gmail.com")
                .specialite("Dentiste")
                .build();


        when(medecinRepository.findById(medecin.getId())).thenReturn(Optional.of(medecin));

        Medecin result = medecinService.findMedecinById(medecin.getId());

        verify(medecinRepository, times(1)).findById(medecin.getId());
        Assertions.assertEquals(medecin, result);
    }



}
