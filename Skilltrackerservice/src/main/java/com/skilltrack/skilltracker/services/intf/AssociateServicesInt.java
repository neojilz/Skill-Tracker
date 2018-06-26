package com.skilltrack.skilltracker.services.intf;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skilltrack.skilltracker.req.AssociateDetails;
import com.skilltrack.skilltracker.req.Response;

@Service
public interface AssociateServicesInt {

	

	ResponseEntity<Response> saveAssociate(MultipartFile file, String associateDet);

	ResponseEntity<AssociateDetails> getAssociateById(String id);

	ResponseEntity<Response> deleteAssociate(AssociateDetails associateDetails);

	ResponseEntity<List<AssociateDetails>> getAllAssociates();

}
