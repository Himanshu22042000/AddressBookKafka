package com.kapturecrm.entity;

import javax.persistence.Entity;

import lombok.Data;


@Data
public class AddressBookDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String emailAddress;
	
	private String mobileNumber;

}
