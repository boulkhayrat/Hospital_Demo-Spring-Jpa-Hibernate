package com.abdelilah.springboot.service;


import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.repositories.ConsultationRepository;
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
public class ConsultationServicesTests {

    @Mock
    private ConsultationRepository consultationRepository;

    @InjectMocks
    private ConsultationServiceImp consultationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePatient() {
        Consultation consultation  = Consultation.builder()
                .rapport("rapport pour la consultation ...")
                .dateConsultation(new Date())
                .build();

        when(consultationRepository.save(consultation)).thenReturn(consultation);

        Consultation savedConsultation = consultationService.saveConsultation(consultation);

        verify(consultationRepository, times(1)).save(consultation);
        Assertions.assertEquals(consultation,savedConsultation);
    }
    @Test
    public void testFindAllPatients() {
        List<Consultation> consultations = new ArrayList<>();
        Consultation consultation  = Consultation.builder()
                .rapport("rapport pour la consultation ...")
                .dateConsultation(new Date())
                .build();
        Consultation consultation1  = Consultation.builder()
                .rapport("rapport pour la consultation ...")
                .dateConsultation(new Date())
                .build();

        consultations.add(consultation);
        consultations.add(consultation1);
        consultationRepository.save(consultation);
        consultationRepository.save(consultation1);

        when(consultationRepository.findAll()).thenReturn(consultations);

        List<Consultation> result = consultationService.findAllConsultations();

        verify(consultationRepository, times(1)).findAll();
        Assertions.assertEquals(consultations, result);
    }

    @Test
    public void testUpdatePatient() {
        Consultation consultation  = Consultation.builder()
                .rapport("rapport pour la consultation ...")
                .dateConsultation(new Date())
                .build();

        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));


        Consultation consultation1 = Consultation.builder()
                .rapport("rapport1 pour la consultation1 ...")
                .dateConsultation(new Date())
                .build();

        Consultation result = consultationService.updateConsultation(consultation1);


        verify(consultationRepository, times(1)).findById(consultation.getId());

        Assertions.assertEquals(consultation1, result);
    }

    @Test
    public void testFindPatientById() {
        Consultation consultation  = Consultation.builder()
                .rapport("rapport pour la consultation ...")
                .dateConsultation(new Date())
                .build();

        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));

        Consultation result = consultationService.findConsultationById(consultation.getId());

        verify(consultationRepository, times(1)).findById(consultation.getId());
        Assertions.assertEquals(consultation, result);
    }



}
