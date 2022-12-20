package com.maiia.pro.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maiia.pro.entity.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, String> {
    List<Appointment> findByPractitionerId(String practitionerId);
    List<Appointment> findAll();
    
    @Query("select ap from Appointment ap where ap.practitionerId = :practitionerId and ap.startDate >= :startDate and ap.startDate < :endDate ")
    Appointment findByPractitionerIdAndStartDateAndEndDate(@Param("practitionerId") Integer practitionerId,@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate);
}
