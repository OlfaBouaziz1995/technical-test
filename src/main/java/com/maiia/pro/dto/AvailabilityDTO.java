package com.maiia.pro.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class AvailabilityDTO implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private Integer practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
