package com.elevysi.site.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class AbstractDAO {
	
	//https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-seven-pagination/
	public Pageable createRequestPage(int pageNo, int size, String sortDirection, String sortField){
		
		return new PageRequest(pageNo, size, sortDirection.equalsIgnoreCase("asc")? Sort.Direction.ASC: Sort.Direction.DESC, sortField);
	}

}
