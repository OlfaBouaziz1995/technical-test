package com.maiia.pro.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// add validation constraint to DTO
public class AppointmentDTO implements Serializable {
		 
		private static final long serialVersionUID = 1L;
		
		private Integer id;
		@NotNull
	    private Integer patientId;
	    
	    @NotNull
	    private Integer practitionerId;
	    @NotNull
	    private LocalDateTime startDate;
	    @NotNull
	    private LocalDateTime endDate;
}
