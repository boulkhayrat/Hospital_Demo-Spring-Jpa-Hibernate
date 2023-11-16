package com.abdelilah.springboot.controller;

import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.service.IRendezVousService;
import com.abdelilah.springboot.web.RendezVousRestController;
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

class RendezVousControllerTests {

    @Mock
    private IRendezVousService rendezVousService;

    @InjectMocks
    private RendezVousRestController rendezVousController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testRendezVousList() {

        List<RendezVous> rendezVouss = new ArrayList<>();
        when(rendezVousService.findAllRendezVous()).thenReturn(rendezVouss);


        List<RendezVous> result = rendezVousController.rendezVousList();


        verify(rendezVousService, times(1)).findAllRendezVous();


        assertEquals(rendezVouss, result);
    }

    @Test
    void testSaveRendezVous() throws Exception {

        RendezVous rendezVous = new RendezVous();
        when(rendezVousService.saveRendezVous(rendezVous)).thenReturn(rendezVous);


        String requestBody = objectMapper.writeValueAsString(rendezVous);
        RendezVous result = rendezVousController.saveRendezVous(rendezVous);


        verify(rendezVousService, times(1)).saveRendezVous(any());


        assertEquals(rendezVous, result);
    }

    @Test
    void testGetRendezVousById() {

        Long rendezVousId = 1L;
        RendezVous rendezVous = new RendezVous();
        when(rendezVousService.findRendezVousById(rendezVousId)).thenReturn(rendezVous);


        ResponseEntity<RendezVous> result = rendezVousController.getRendezVousById(rendezVousId);


        verify(rendezVousService, times(1)).findRendezVousById(rendezVousId);


        assertEquals(ResponseEntity.ok(rendezVous), result);
    }

    @Test
    void testUpdateRendezVousById() throws Exception {

        Long rendezVousId = 1L;
        RendezVous rendezVousDetails = new RendezVous();
        when(rendezVousService.updateRendezVous(any())).thenReturn(rendezVousDetails);


        String requestBody = objectMapper.writeValueAsString(rendezVousDetails);
        ResponseEntity<RendezVous> result = rendezVousController.updateRendezVous(rendezVousId, rendezVousDetails);


        verify(rendezVousService, times(1)).updateRendezVous(any());


        assertEquals(ResponseEntity.ok(rendezVousDetails), result);
    }

    @Test
    void testDeleteRendezVous() {

        Long rendezVousId = 1L;
        doNothing().when(rendezVousService).deleteRendezVous(rendezVousId);


        ResponseEntity<Map<String, Boolean>> result = rendezVousController.deleteRendezVous(rendezVousId);


        verify(rendezVousService, times(1)).deleteRendezVous(rendezVousId);


        assertEquals(ResponseEntity.ok(Map.of("deleted", true)), result);
    }
}
