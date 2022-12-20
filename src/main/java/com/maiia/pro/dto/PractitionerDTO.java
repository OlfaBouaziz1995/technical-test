package com.maiia.pro.dto;

import java.io.Serializable;

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
public class PractitionerDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String firstName;
    private String lastName;
    private String speciality;
}
