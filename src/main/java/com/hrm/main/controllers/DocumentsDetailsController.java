
package com.hrm.main.controllers;

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

import com.hrm.main.models.DocumentDetails;
import com.hrm.main.models.DocumentUpload;
import com.hrm.main.services.IDocumentsDetailsService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

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

	@GetMapping("/get/{docId}")
	public ResponseEntity<List<DocumentUpload>> getDocumentsDetailsById(@PathVariable Integer docId) {
		List<DocumentDetails> documents = this.docDeatilsService.getDocumentsDetById(docId);
		return new ResponseEntity<List<DocumentUpload>>(HttpStatus.OK);

	}

}
