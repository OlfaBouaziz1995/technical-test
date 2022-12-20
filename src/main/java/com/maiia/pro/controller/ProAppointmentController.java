package com.maiia.pro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiia.pro.dto.AppointmentDTO;
import com.maiia.pro.exception.AppointmentErrorException;
import com.maiia.pro.exception.RessourceNotFoundException;
import com.maiia.pro.service.ProAppointmentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAppointmentController {
    @Autowired
    private ProAppointmentService proAppointmentService;
    

    @ApiOperation(value = "Get appointments by practitionerId")
    @GetMapping("/{practitionerId}")
    public List<AppointmentDTO> getAppointmentsByPractitioner(@PathVariable final Integer practitionerId) {
        return proAppointmentService.findByPractitionerId(practitionerId);
    }

    @ApiOperation(value = "Get all appointments")
    @GetMapping
    public List<AppointmentDTO> getAppointments() {
        return proAppointmentService.findAll();
    }
    
    @ApiOperation(value = "Create a new appointment")
    @PostMapping("/appointment/save")
    public AppointmentDTO createAnAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) throws RessourceNotFoundException, AppointmentErrorException {
        return proAppointmentService.createAppointment(appointmentDTO);
    }
}
