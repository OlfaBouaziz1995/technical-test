package com.maiia.pro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.maiia.pro.dto.TimeSlotDTO;
import com.maiia.pro.entity.TimeSlot;
import com.maiia.pro.repository.TimeSlotRepository;

@Service
public class ProTimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public List<TimeSlotDTO> findByPractitionerId(Integer practitionerId) {
    	List<TimeSlot> timeSlotList = timeSlotRepository.findByPractitionerId(practitionerId);
		if (!CollectionUtils.isEmpty(timeSlotList)) {
			return timeSlotList.stream()
					.map(t -> modelMapper.map(t, TimeSlotDTO.class)).collect(Collectors.toList());
		}
        return List.of();
    }
}
