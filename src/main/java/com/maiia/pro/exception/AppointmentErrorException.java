package com.maiia.pro.exception;

public class AppointmentErrorException extends Exception {
    
	 public AppointmentErrorException(String errorMessage) {
         super(errorMessage);
     }
}