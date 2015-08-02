package org.tpark.visitor.pass.mngmt.svcs;

import java.io.IOException;
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
		BeanUtils.copyProperties(guest, guestEntity);
		long passId=vRepo.savePass(guestEntity);
		vRepo.saveImage(guestEntity);		
		
		
		try {
			testRepo.getImage(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(passId, headers, HttpStatus.OK);
	
	}

	
	
	@RequestMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Visitor>> search(@RequestBody SearchCritera criteria){	
		List<Visitor> result = vRepo.search(criteria);
		
		/*VisitorJsonObject vj = new VisitorJsonObject();
		vj.setiTotalDisplayRecords(500);
		//Set Total record
		vj.setiTotalRecords(500);
		vj.setAaData(result)*/;
		HttpHeaders headers = new HttpHeaders();
		 ObjectMapper mapper = new ObjectMapper();
	       /* String json = "";
	        try {
	            json = mapper.writeValueAsString(result);
	        } catch (JsonProcessingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println(json);*/
		return new ResponseEntity<List<Visitor>>(result, headers, HttpStatus.OK);
	
	}
}
