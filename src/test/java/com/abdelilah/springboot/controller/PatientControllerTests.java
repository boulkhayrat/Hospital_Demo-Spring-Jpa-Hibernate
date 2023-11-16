package com.abdelilah.springboot.controller;

import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.service.IPatientService;
import com.abdelilah.springboot.web.PatientRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientControllerTests {

    @Mock
    private IPatientService patientService;

    @InjectMocks
    private PatientRestController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testPatientList() {

        List<Patient> patients = new ArrayList<>();
        when(patientService.findAllPatients()).thenReturn(patients);


        List<Patient> result = patientController.patientList();


        verify(patientService, times(1)).findAllPatients();


        assertEquals(patients, result);
    }

    @Test
    void testSavePatient() throws Exception {

        Patient patient = new Patient();
        when(patientService.savePatient(patient)).thenReturn(patient);


        String requestBody = objectMapper.writeValueAsString(patient);
        Patient result = patientController.savePatient(patient);


        verify(patientService, times(1)).savePatient(any());


        assertEquals(patient, result);
    }

    @Test
    void testGetPatientById() {

        Long patientId = 1L;
        Patient patient = new Patient();
        when(patientService.findPatientById(patientId)).thenReturn(patient);


        ResponseEntity<Patient> result = patientController.getPatientById(patientId);


        verify(patientService, times(1)).findPatientById(patientId);


        assertEquals(ResponseEntity.ok(patient), result);
    }

    @Test
    void testUpdatePatientById() throws Exception {

        Long patientId = 1L;
        Patient patientDetails = new Patient();
        when(patientService.updatePatient(any())).thenReturn(patientDetails);


        String requestBody = objectMapper.writeValueAsString(patientDetails);
        ResponseEntity<Patient> result = patientController.updatePatientById(patientId, patientDetails);


        verify(patientService, times(1)).updatePatient(any());


        assertEquals(ResponseEntity.ok(patientDetails), result);
    }

    @Test
    void testDeletePatient() {

        Long patientId = 1L;
        doNothing().when(patientService).deletePatient(patientId);


        ResponseEntity<Map<String, Boolean>> result = patientController.deletePatient(patientId);


        verify(patientService, times(1)).deletePatient(patientId);


        assertEquals(ResponseEntity.ok(Map.of("deleted", true)), result);
    }
}
