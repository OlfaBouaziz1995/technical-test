package com.maiia.pro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maiia.pro.EntityFactory;
import com.maiia.pro.dto.AppointmentDTO;
import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.exception.AppointmentErrorException;
import com.maiia.pro.exception.RessourceNotFoundException;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.PractitionerRepository;

@SpringBootTest
public class ProAppointmentServiceTest {
	
	  private final  EntityFactory entityFactory = new EntityFactory();
	    private  final static Integer patient_id=657679;

	    @Autowired
	    private ProAppointmentService proAppointmentService;
	    
	    @Autowired
	    private AppointmentRepository appointmentRepository;
	    
	    @Autowired
	    private PractitionerRepository practitionerRepository;
	    
	    @Autowired
	    ModelMapper modelMapper;
	    
	    @BeforeEach 
	    void init() {
	    	appointmentRepository.deleteAll();
	    }

	    @Test
	    void createAppointment() throws RessourceNotFoundException, AppointmentErrorException {
	        Practitioner practitioner = practitionerRepository.save(entityFactory.createPractitioner());
	        LocalDateTime startDate = LocalDateTime.of(2020, Month.FEBRUARY, 5, 11, 0, 0);
	        AppointmentDTO appDTO = AppointmentDTO.builder().practitionerId(practitioner.getId())
	        		.patientId(patient_id).startDate(startDate).endDate(startDate.plusMinutes(15)).build();

	        AppointmentDTO newApptmentDTO = proAppointmentService.createAppointment(appDTO);

	        assertEquals(newApptmentDTO.getPatientId(), appDTO.getPatientId());
	        assertEquals(newApptmentDTO.getPractitionerId(), appDTO.getPractitionerId());
	        assertEquals(newApptmentDTO.getStartDate(),appDTO.getStartDate());
	        assertEquals(newApptmentDTO.getEndDate(),appDTO.getEndDate());

	        
	    }
	    
	    @Test
	    void checkAppointmentsAreNotDuplicated() throws RessourceNotFoundException, AppointmentErrorException {
	        Practitioner practitioner = practitionerRepository.save(entityFactory.createPractitioner());
	        LocalDateTime startDate = LocalDateTime.of(2020, Month.FEBRUARY, 5, 11, 0, 0);
	        AppointmentDTO appDTO = AppointmentDTO.builder().practitionerId(practitioner.getId())
	        		.patientId(patient_id).startDate(startDate).endDate(startDate.plusMinutes(15)).build();

	         proAppointmentService.createAppointment(appDTO);
	        Throwable exception = Assertions.assertThrows(AppointmentErrorException.class, () -> proAppointmentService.createAppointment(appDTO));
	        assertEquals("appointment already exist!", exception.getMessage());
	    }
	    
	    

		@Test
	    void findAllAppointment() throws RessourceNotFoundException, AppointmentErrorException {
	        Practitioner practitioner = practitionerRepository.save(entityFactory.createPractitioner());
	        LocalDateTime startDate = LocalDateTime.of(2020, Month.FEBRUARY, 5, 11, 0, 0);
	        AppointmentDTO appDTO = AppointmentDTO.builder().practitionerId(practitioner.getId())
	        		.patientId(patient_id).startDate(startDate).endDate(startDate.plusMinutes(15)).build();

	        proAppointmentService.createAppointment(appDTO);
	        List<AppointmentDTO> apptmentDTOList = proAppointmentService.findAll();
	        assertEquals(1,apptmentDTOList.size());
	        
	    }
		
		@Test
	    void findByPractitionerId() throws RessourceNotFoundException, AppointmentErrorException {
	        Practitioner practitioner = practitionerRepository.save(entityFactory.createPractitioner());
	        LocalDateTime startDate = LocalDateTime.of(2020, Month.FEBRUARY, 5, 11, 0, 0);
	        AppointmentDTO appDTO = AppointmentDTO.builder().practitionerId(practitioner.getId())
	        		.patientId(patient_id).startDate(startDate).endDate(startDate.plusMinutes(15)).build();

	        proAppointmentService.createAppointment(appDTO);
	        List<AppointmentDTO> apptmentDTOList = proAppointmentService.findByPractitionerId(practitioner.getId());
	        assertEquals(1,apptmentDTOList.size());
	        
	    }
	    
	    


}
