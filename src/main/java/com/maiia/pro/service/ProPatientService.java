package com.maiia.pro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.maiia.pro.dto.PatientDTO;
import com.maiia.pro.entity.Patient;
import com.maiia.pro.repository.PatientRepository;

@Service
public class ProPatientService {
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public PatientDTO find(String patientId) {
        return modelMapper.map(patientRepository.findById(patientId).orElseThrow(), PatientDTO.class);
    }

    public List<PatientDTO> findAll() {
    	List<Patient> patientList = patientRepository.findAll();
		if (!CollectionUtils.isEmpty(patientList)) {
			return patientList.stream()
					.map(pt -> modelMapper.map(pt, PatientDTO.class)).collect(Collectors.toList());
		}
		return List.of();
    }
}
