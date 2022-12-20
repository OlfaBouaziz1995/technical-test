package com.maiia.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maiia.pro.dto.AvailabilityDTO;
import com.maiia.pro.service.ProAvailabilityService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/availabilities", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAvailabilityController {
    @Autowired
    private ProAvailabilityService proAvailabilityService;

    @ApiOperation(value = "Get availabilities by practitionerId")
    @GetMapping
    public List<AvailabilityDTO> getAvailabilities(@RequestParam final Integer practitionerId) {
        return proAvailabilityService.findByPractitionerId(practitionerId);
    }
    
    @ApiOperation(value = "Generate availibilities")
    @GetMapping("/generated")
    public List<AvailabilityDTO> generateAvailabilities(@RequestParam final Integer practitionerId) {
        return proAvailabilityService.generateAvailabilities(practitionerId);
    }
}
