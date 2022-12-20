package com.maiia.pro.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.entity.Availability;
import com.maiia.pro.entity.TimeSlot;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;
import com.maiia.pro.repository.TimeSlotRepository;

@Service
public class ProAvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<Availability> findByPractitionerId(Integer practitionerId) {
        return availabilityRepository.findByPractitionerId(practitionerId);
    }

    public List<Availability> generateAvailabilities(Integer practitionerId) {
		List<TimeSlot> timeSlotList = timeSlotRepository.findByPractitionerId(practitionerId);
		if (!CollectionUtils.isEmpty(timeSlotList)) {
			for (TimeSlot time : timeSlotList) {
				int i = 0;
				int j = 0;
				LocalDateTime startDate = time.getStartDate();
				while (i < 60 && j < 60) {
					j = i + 15 > 60 ? 60 : i + 15;
					Availability newAvailibility = null;
					Availability avbility = availabilityRepository.findByPractitionerIdAndStartDateAndEndDate(
							practitionerId, startDate.plusMinutes(i), startDate.plusMinutes(j));
					Appointment apptment = appointmentRepository.findByPractitionerIdAndStartDateAndEndDate(
							practitionerId, startDate.plusMinutes(i), startDate.plusMinutes(j));
					if (avbility == null && apptment == null) {
						newAvailibility = availabilityRepository
								.save(Availability.builder().practitionerId(time.getPractitionerId())
										.startDate(startDate.plusMinutes(i)).endDate(startDate.plusMinutes(j)).build());

					} else if (avbility == null && apptment != null) {
						newAvailibility = availabilityRepository
								.save(Availability.builder().practitionerId(time.getPractitionerId())
										.startDate(apptment.getStartDate()).endDate(apptment.getEndDate()).build());

					}
					if (apptment != null) {
						Integer id = newAvailibility != null ? newAvailibility.getId() : avbility.getId();
						availabilityRepository.deleteById(id);
					}
					int minutes = newAvailibility != null ? newAvailibility.getEndDate().getMinute()
							: avbility.getEndDate().getMinute();
					i = minutes == 0 ? 60 : minutes;

				}
			}
		}
		return findByPractitionerId(practitionerId);
	}
}
