package com.maiia.pro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.maiia.pro.dto.AppointmentDTO;
import com.maiia.pro.entity.Appointment;
import com.maiia.pro.exception.AppointmentErrorException;
import com.maiia.pro.exception.RessourceNotFoundException;
import com.maiia.pro.repository.AppointmentRepository;

@Service
public class ProAppointmentService {
	
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public AppointmentDTO find(String appointmentId) throws RessourceNotFoundException {
    	Optional<Appointment> app = appointmentRepository.findById(appointmentId);
    	if(app.isPresent()) {
    		return modelMapper.map(appointmentRepository.findById(appointmentId),AppointmentDTO.class);
    	}
    	throw new RessourceNotFoundException("No appointment found with id " +appointmentId);
    }
    
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws RessourceNotFoundException, AppointmentErrorException {
    	
    	Appointment apptmnt = modelMapper.map(appointmentDTO,Appointment.class);
    	if(apptmnt != null) {
	    	if(appointmentRepository.findByPractitionerIdAndStartDateAndEndDate(apptmnt.getPractitionerId(), apptmnt.getStartDate(), apptmnt.getEndDate()) ==null) {
	    		return modelMapper.map(appointmentRepository.save(apptmnt),AppointmentDTO.class);
	    	}
	    		throw new AppointmentErrorException("appointment already exist!");
    	}
    	throw new RessourceNotFoundException("Enter a new appointment!");
    }

    public List<AppointmentDTO> findAll() {
		List<Appointment> apptmentList = appointmentRepository.findAll();
		if (!CollectionUtils.isEmpty(apptmentList)) {
			return apptmentList.stream()
					.map(apptment -> modelMapper.map(apptment, AppointmentDTO.class)).collect(Collectors.toList());
		}
		return List.of();
	}

    public List<AppointmentDTO> findByPractitionerId(Integer practitionerId) {
		List<Appointment> apptmentList = appointmentRepository.findByPractitionerId(practitionerId);
		if (!CollectionUtils.isEmpty(apptmentList)) {
			return apptmentList.stream()
					.map(apptment -> modelMapper.map(apptment, AppointmentDTO.class)).collect(Collectors.toList());
		}
		return List.of();
	}
}
