package org.tpark.visitor.pass.mngmt.svcs;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tpark.visitor.pass.mngmt.svcs.model.SearchCritera;
import org.tpark.visitor.pass.mngmt.svcs.model.Visitor;
import org.tpark.visitor.pass.mngmt.svcs.model.VisitorEntity;
import org.tpark.visitor.pass.mngmt.svcs.model.VisitorJsonObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
@RequestMapping("/visitor")
public class VisitorServiceHandler{
	
	@Autowired
 	private VisitorRepository vRepo;
	
	@Autowired
 	private TestVisitorRepository testRepo;
 	
	@RequestMapping(value="/save",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity savePass(@RequestBody Visitor guest){	
		VisitorEntity guestEntity = new VisitorEntity();
		guest.setBuilding(guest.getBuildingstr().trim().replace("\n", ""));
		BeanUtils.copyProperties(guest, guestEntity);
		long passId=0;
		try {
			passId = vRepo.savePass(guestEntity);
			vRepo.saveImage(guestEntity);
		} catch (Exception e) {
			System.out.println("-- SAVE FAILED --"+e.getMessage());
			e.printStackTrace();
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity(e.getMessage(), headers, HttpStatus.SERVICE_UNAVAILABLE);
		}		
		
	
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(passId, headers, HttpStatus.OK);
	
	}

	
	
	@RequestMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Visitor>> search(@RequestBody SearchCritera criteria){
		if(!validate(criteria)){
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity("Something Wrong", headers, HttpStatus.PRECONDITION_FAILED);
			
		}
		List<Visitor> result=Collections.emptyList();
		try {
			result = vRepo.search(criteria);
		} catch (Exception e) {
			System.out.println("-- SEARCH FAILED --"+e.getMessage());
			e.printStackTrace();
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity(e.getMessage(), headers, HttpStatus.SERVICE_UNAVAILABLE);
		}
		HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<List<Visitor>>(result, headers, HttpStatus.OK);
	
	}



	private boolean validate(SearchCritera criteria) {
		if(criteria.getPassNum().isEmpty() && criteria.getCompany().isEmpty() && criteria.getBuilding().isEmpty()
				&& criteria.getFromDate().isEmpty()&& criteria.getToDate().isEmpty())
			return false;
		return true;
	}
}
