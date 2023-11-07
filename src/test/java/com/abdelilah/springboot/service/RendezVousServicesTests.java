package com.abdelilah.springboot.service;


import com.abdelilah.springboot.entities.Patient;
import com.abdelilah.springboot.entities.RendezVous;
import com.abdelilah.springboot.entities.StatusRDV;
import com.abdelilah.springboot.repositories.PatientRepository;
import com.abdelilah.springboot.repositories.RendezVousRepository;
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
public class RendezVousServicesTests {

    @Mock
    private RendezVousRepository rendezVousRepository;

    @InjectMocks
    private RendezVousServiceImp rendezVousService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePatient() {
        RendezVous rendezVous = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.DONE)
                .build();
        when(rendezVousRepository.save(rendezVous)).thenReturn(rendezVous);

        RendezVous savedRendezVous = rendezVousService.saveRendezVous(rendezVous);

        verify(rendezVousRepository, times(1)).save(rendezVous);
        Assertions.assertEquals(rendezVous,savedRendezVous);
    }
    @Test
    public void testFindAllPatients() {
        List<RendezVous> rendezVousList = new ArrayList<>();
        RendezVous rendezVous = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.DONE)
                .build();
        RendezVous rendezVous1 = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.CANCELED)
                .build();

        rendezVousList.add(rendezVous);
        rendezVousList.add(rendezVous1);
        rendezVousRepository.save(rendezVous);
        rendezVousRepository.save(rendezVous1);

        when(rendezVousRepository.findAll()).thenReturn(rendezVousList);

        List<RendezVous> result = rendezVousService.findAllRendezVous();

        verify(rendezVousRepository, times(1)).findAll();
        Assertions.assertEquals(rendezVousList, result);
    }

    @Test
    public void testUpdatePatient() {
        RendezVous rendezVous = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.DONE)
                .build();

        when(rendezVousRepository.findById(rendezVous.getId())).thenReturn(Optional.of(rendezVous));


        RendezVous rendezVous1 = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.PENDING)
                .build();

        RendezVous result = rendezVousService.updateRendezVous(rendezVous1);


        verify(rendezVousRepository, times(1)).findById(rendezVous.getId());

        Assertions.assertEquals(rendezVous1, result);
    }

    @Test
    public void testFindPatientById() {
        RendezVous rendezVous = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.DONE)
                .build();

        when(rendezVousRepository.findById(rendezVous.getId())).thenReturn(Optional.of(rendezVous));

        RendezVous result = rendezVousService.findRendezVousById(rendezVous.getId());

        verify(rendezVousRepository, times(1)).findById(rendezVous.getId());
        Assertions.assertEquals(rendezVous, result);
    }



}
