package com.abdelilah.springboot.service;


import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.exception.ResourceNotFoundException;
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
public class PatientServicesTests {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImp patientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePatient() {
        Patient patient = Patient.builder()
                .nom("Hamid")
                .malade(true)
                .dateNaissance(new Date())
                .build();
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.savePatient(patient);

        verify(patientRepository, times(1)).save(patient);
        Assertions.assertEquals(patient,savedPatient);
    }
    @Test
    public void testFindAllPatients() {
        List<Patient> patients = new ArrayList<>();
        Patient patient = Patient.builder()
                .nom("Hamid")
                .malade(true)
                .dateNaissance(new Date())
                .build();
        Patient patient1= Patient.builder()
                .nom("Adel")
                .malade(true)
                .dateNaissance(new Date())
                .build();

        patients.add(patient);
        patients.add(patient1);
        patientRepository.save(patient);
        patientRepository.save(patient1);

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.findAllPatients();

        verify(patientRepository, times(1)).findAll();
        Assertions.assertEquals(patients, result);
    }

    @Test
    public void testUpdatePatient() {
        Patient patient = Patient.builder()
                .nom("Abdelilah")
                .malade(true)
                .dateNaissance(new Date())
                .build();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));


        Patient patient1 = Patient.builder()
                .nom("Hamid")
                .malade(true)
                .dateNaissance(new Date())
                .build();

        Patient result = patientService.updatePatient(patient1);


        verify(patientRepository, times(1)).findById(patient.getId());

        Assertions.assertEquals(patient1, result);
    }

    @Test
    public void testFindPatientById() {
        Patient patient = Patient.builder()
                .nom("Abdelilah")
                .malade(true)
                .dateNaissance(new Date())
                .build();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        Patient result = patientService.findPatientById(patient.getId());

        verify(patientRepository, times(1)).findById(patient.getId());
        Assertions.assertEquals(patient, result);
    }

    @Test
    public void testDeletePatient(){
        Patient patient = Patient.builder()
                .nom("Abdelilah")
                .malade(true)
                .dateNaissance(new Date())
                .build();

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        patientService.deletePatient(patient.getId());
        verify(patientRepository, times(1)).findById(patient.getId());
    }
    @Test
    public void testIsNameValid(){
        Assertions.assertTrue(patientService.isNameValid("ABDELILAH"));
        Assertions.assertTrue(patientService.isNameValid("Abdelilah"));
        Assertions.assertFalse(patientService.isNameValid("ABDELILAH@"));
        Assertions.assertFalse(patientService.isNameValid("1234"));
        Assertions.assertFalse(patientService.isNameValid("@ABDELILAH"));
        Assertions.assertFalse(patientService.isNameValid(""));
        Assertions.assertFalse(patientService.isNameValid(null));
    }



}
