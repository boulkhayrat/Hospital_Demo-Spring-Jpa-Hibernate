package com.abdelilah.springboot.controller;

import com.abdelilah.springboot.entities.Consultation;
import com.abdelilah.springboot.service.IConsultationService;
import com.abdelilah.springboot.web.ConsultationRestController;
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

class ConsultationControllerTests {

    @Mock
    private IConsultationService consultationService;

    @InjectMocks
    private ConsultationRestController consultationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testConsultationList() {

        List<Consultation> consultations = new ArrayList<>();
        when(consultationService.findAllConsultations()).thenReturn(consultations);


        List<Consultation> result = consultationController.consultationList();


        verify(consultationService, times(1)).findAllConsultations();


        assertEquals(consultations, result);
    }

    @Test
    void testSaveConsultation() throws Exception {

        Consultation consultation = new Consultation();
        when(consultationService.saveConsultation(consultation)).thenReturn(consultation);


        String requestBody = objectMapper.writeValueAsString(consultation);
        Consultation result = consultationController.saveConsultation(consultation);


        verify(consultationService, times(1)).saveConsultation(any());


        assertEquals(consultation, result);
    }

    @Test
    void testGetConsultationById() {

        Long ConsultationId = 1L;
        Consultation Consultation = new Consultation();
        when(consultationService.findConsultationById(ConsultationId)).thenReturn(Consultation);


        ResponseEntity<Consultation> result = consultationController.getConsultationById(ConsultationId);


        verify(consultationService, times(1)).findConsultationById(ConsultationId);


        assertEquals(ResponseEntity.ok(Consultation), result);
    }

    @Test
    void testUpdateConsultationById() throws Exception {

        Long ConsultationId = 1L;
        Consultation ConsultationDetails = new Consultation();
        when(consultationService.updateConsultation(any())).thenReturn(ConsultationDetails);


        String requestBody = objectMapper.writeValueAsString(ConsultationDetails);
        ResponseEntity<Consultation> result = consultationController.updateConsultation(ConsultationId, ConsultationDetails);


        verify(consultationService, times(1)).updateConsultation(any());


        assertEquals(ResponseEntity.ok(ConsultationDetails), result);
    }

    @Test
    void testDeleteConsultation() {

        Long ConsultationId = 1L;
        doNothing().when(consultationService).deleteConsultation(ConsultationId);


        ResponseEntity<Map<String, Boolean>> result = consultationController.deleteConsultation(ConsultationId);


        verify(consultationService, times(1)).deleteConsultation(ConsultationId);


        assertEquals(ResponseEntity.ok(Map.of("deleted", true)), result);
    }
}
