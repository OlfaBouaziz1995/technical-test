package com.maiia.pro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.maiia.pro.dto.PractitionerDTO;
import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.repository.PractitionerRepository;

@Service
public class ProPractitionerService {
    @Autowired
    private PractitionerRepository practitionerRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public PractitionerDTO find(String practitionerId) {
        return modelMapper.map(practitionerRepository.findById(practitionerId).orElseThrow(), PractitionerDTO.class);
    }

    public List<PractitionerDTO> findAll() {
    	List<Practitioner> practitionerList = practitionerRepository.findAll();
		if (!CollectionUtils.isEmpty(practitionerList)) {
			return practitionerList.stream()
					.map(pt -> modelMapper.map(pt, PractitionerDTO.class)).collect(Collectors.toList());
		}
		return List.of();
    }
}
