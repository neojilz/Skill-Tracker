package com.skilltrack.skilltracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skilltrack.skilltracker.req.AssociateDetails;
import com.skilltrack.skilltracker.req.Response;
import com.skilltrack.skilltracker.services.intf.AssociateServicesInt;


@RestController
@CrossOrigin(origins = "*")
public class AssociateController {

	@Autowired
	AssociateServicesInt associateService;

	@RequestMapping(value = "/associates/getall", method = RequestMethod.GET)
	public ResponseEntity<List<AssociateDetails>> getAllAssociates() {

		return associateService.getAllAssociates();

	}

	@RequestMapping(value = "/associates/save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response> saveAssociate(@RequestParam("file") MultipartFile file,@RequestParam("associateDetails") String associateDet ) {
		
		return associateService.saveAssociate(file,associateDet);

	}
	
	

	@RequestMapping(value = "/associates/getasociate/{id}", method = RequestMethod.GET)
	public ResponseEntity<AssociateDetails> getAssociateById(@PathVariable String id) {

		return associateService.getAssociateById(id);

	}

	@RequestMapping(value = "/associates/delete", method = RequestMethod.POST)
	public ResponseEntity<Response> deleteAssociate(@RequestBody AssociateDetails associateDetails) {

		return associateService.deleteAssociate(associateDetails);

	}
}
