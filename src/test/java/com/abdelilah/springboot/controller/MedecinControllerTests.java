package com.abdelilah.springboot.controller;

import com.abdelilah.springboot.entities.Medecin;
import com.abdelilah.springboot.service.IMedecinService;
import com.abdelilah.springboot.web.MedecinRestController;
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

class MedecinControllerTests {

    @Mock
    private IMedecinService medecinService;

    @InjectMocks
    private MedecinRestController medecinController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testMedecinList() {

        List<Medecin> medecins = new ArrayList<>();
        when(medecinService.findAllMedecins()).thenReturn(medecins);


        List<Medecin> result = medecinController.medecinList();


        verify(medecinService, times(1)).findAllMedecins();


        assertEquals(medecins, result);
    }

    @Test
    void testSaveMedecin() throws Exception {

        Medecin medecin = new Medecin();
        when(medecinService.saveMedecin(medecin)).thenReturn(medecin);


        String requestBody = objectMapper.writeValueAsString(medecin);
        Medecin result = medecinController.saveMedecin(medecin);


        verify(medecinService, times(1)).saveMedecin(any());


        assertEquals(medecin, result);
    }

    @Test
    void testGetMedecinById() {

        Long MedecinId = 1L;
        Medecin Medecin = new Medecin();
        when(medecinService.findMedecinById(MedecinId)).thenReturn(Medecin);


        ResponseEntity<Medecin> result = medecinController.getMedecinById(MedecinId);


        verify(medecinService, times(1)).findMedecinById(MedecinId);


        assertEquals(ResponseEntity.ok(Medecin), result);
    }

    @Test
    void testUpdateMedecinById() throws Exception {

        Long MedecinId = 1L;
        Medecin MedecinDetails = new Medecin();
        when(medecinService.updateMedecin(any())).thenReturn(MedecinDetails);


        String requestBody = objectMapper.writeValueAsString(MedecinDetails);
        ResponseEntity<Medecin> result = medecinController.updateMedecinById(MedecinId, MedecinDetails);


        verify(medecinService, times(1)).updateMedecin(any());


        assertEquals(ResponseEntity.ok(MedecinDetails), result);
    }

    @Test
    void testDeleteMedecin() {

        Long MedecinId = 1L;
        doNothing().when(medecinService).deleteMedecin(MedecinId);


        ResponseEntity<Map<String, Boolean>> result = medecinController.deleteMedecin(MedecinId);


        verify(medecinService, times(1)).deleteMedecin(MedecinId);


        assertEquals(ResponseEntity.ok(Map.of("deleted", true)), result);
    }
}
