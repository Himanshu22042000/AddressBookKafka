package com.kapturecrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapturecrm.entity.AddressBookDTO;
import com.kapturecrm.entity.AddressBookEntity;
import com.kapturecrm.repository.AddressBookRepository;
import com.kapturecrm.service.AddressBookService;

@RequestMapping("/user")
@RestController
public class AddressBookController {
	
	@Autowired
	private AddressBookService addressBookService;
	
	@Autowired
	private AddressBookRepository addressBookRepository;
	
	@Autowired
	private KafkaTemplate<String,List<AddressBookEntity>> kafkaTemplate;
	
	private static final String TOPIC = "addressbook1";
	
	 
	@PostMapping("/saveEntries")
	public ResponseEntity<?> saveAddressEntity(@RequestBody AddressBookEntity addressBookEntity)
	{
	 AddressBookEntity addressBookEntity2 = addressBookService.saveEntries(addressBookEntity);
	 kafkaTemplate.send(TOPIC, (List<AddressBookEntity>) addressBookEntity2);
     return new ResponseEntity<>(addressBookEntity2, HttpStatus.OK);	
	}
	
	
	@GetMapping("/list")
	public List<AddressBookEntity> addressBookEntity ()
	{
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>) addressBookRepository.findAll());
		return addressBookService.finAll();
	}
	
	@GetMapping("/lastName")
	public List<AddressBookEntity> AddressBookDTOByLastName (String lastName)
	{
		System.out.print(lastName);
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>) addressBookService.findByLastName(lastName));
		return (List<AddressBookEntity>)addressBookService.findByLastName(lastName);	
	}
	
	@GetMapping("/id")
	public AddressBookEntity findById(long id)
	{
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>) addressBookRepository.findById(id));
		return addressBookRepository.findById(id);
	}
	
	@GetMapping("/deleteId/id")
	public String deleteById(long id)
	{
		AddressBookEntity addressBookEntity = addressBookRepository.findById(id);
		if(addressBookEntity!=null)
		{
			kafkaTemplate.send(TOPIC,"Deleted Successfully", null);
		addressBookRepository.deleteById(id);
		return "Deleted Successfully";
	      }
		else
		{
			return "Record Not Found";
		}
}
}
