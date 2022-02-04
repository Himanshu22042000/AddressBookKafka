package com.kapturecrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kapturecrm.entity.AddressBookDTO;
import com.kapturecrm.entity.AddressBookEntity;


@Service
public interface AddressBookService {
	public AddressBookEntity saveEntries(AddressBookEntity addressBookEntity);
	
	public List<AddressBookEntity> finAll();

	public List<AddressBookEntity> findByLastName(String lastName);
	
	//public AddressBookEntity findById(String id);
	
	
	
	
}
