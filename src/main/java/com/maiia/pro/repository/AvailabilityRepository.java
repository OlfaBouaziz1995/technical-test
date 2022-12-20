package com.maiia.pro.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maiia.pro.entity.Availability;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Integer> {
    List<Availability> findByPractitionerId(Integer id);
    
    @Query("select av from Availability av where av.practitionerId = :practitionerId and av.startDate >= :startDate and av.startDate < :endDate ")
    Availability findByPractitionerIdAndStartDateAndEndDate(@Param("practitionerId") Integer practitionerId,@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate);
}
