package com.kapturecrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kapturecrm.entity.AddressBookDTO;
import com.kapturecrm.entity.AddressBookEntity;



public interface AddressBookRepository extends JpaRepository<AddressBookEntity, Long>
{
	
	//@Query(value = "SELECT p.first_name as firstName, p.last_name as lastName  , p.email_address as emailaddress , p.mobile_number as mobileNumber FROM address_table p WHERE last_name = :last_name", nativeQuery = true)
    public List<AddressBookEntity> findByLastName(String lastName);
	
	public AddressBookEntity findById(long id);
	
	
}
