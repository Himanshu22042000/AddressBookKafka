package com.kapturecrm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapturecrm.entity.AddressBookDTO;
import com.kapturecrm.entity.AddressBookEntity;
import com.kapturecrm.repository.AddressBookRepository;
import com.kapturecrm.service.AddressBookService;

@Service
public class AddressBookServiceImpl implements AddressBookService {
	
	
	@Autowired
	private AddressBookRepository addressBookRepository;

	@Override
	public AddressBookEntity saveEntries(AddressBookEntity addressBookEntity) {
	addressBookRepository.save(addressBookEntity);
	return addressBookEntity;
	}

	@Override
	public List<AddressBookEntity> finAll() {
		return addressBookRepository.findAll();
	}

	@Override
	public List<AddressBookEntity> findByLastName(String lastName) {
		return (List<AddressBookEntity>) addressBookRepository.findByLastName(lastName);
	}


}
