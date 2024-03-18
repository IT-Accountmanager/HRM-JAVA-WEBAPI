
package com.hrm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.models.DocumentDetails;
import com.hrm.models.DocumentUpload;
import com.hrm.services.IDocumentsDetailsService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController

@RequestMapping("/DocumentsDetails")
public class DocumentsDetailsController {

	@Autowired
	IDocumentsDetailsService docDeatilsService;

	@PostMapping("/Add")
	public ResponseEntity<String> addDocumentDetails(@RequestBody DocumentDetails docDetails) {
		String result = docDeatilsService.addDocuments(docDetails);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/get/{docId}") public ResponseEntity<DocumentUpload>
	 * getDocumentsDetailsById(@PathVariable Integer docId) { DocumentDetails
	 * documents = this.docDeatilsService.getDocumentsDetById(docId); return new
	 * ResponseEntity<DocumentUpload>(documents,HttpStatus.OK); }
	 */
}