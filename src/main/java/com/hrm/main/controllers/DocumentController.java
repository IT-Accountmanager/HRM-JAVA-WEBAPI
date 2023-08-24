package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Document;
import com.hrm.main.services.IDocumentService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/document")

public class DocumentController {

	@Autowired
	IDocumentService documentService;

	@PostMapping("/addDocument")
	public ResponseEntity<String> createDocument(@RequestBody Document doc) {

		String result = this.documentService.createDocument(doc);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/getDocument")
	public ResponseEntity<List<Document>> getAllDocument() {
		List<Document> allDocument = this.documentService.getAllDocument();
		return new ResponseEntity<List<Document>>(allDocument, HttpStatus.OK);
	}

	@GetMapping("/getDocument/{id}")
	public ResponseEntity<Document> getDocumentById(@PathVariable Integer id) {
		Document doc = documentService.getDocument(id);
		return new ResponseEntity<Document>(doc, HttpStatus.OK);
	}

	@DeleteMapping("/deleteDocument/{id}")
	public ResponseEntity<String> deleteDocument(@PathVariable Integer id) {
		String result = documentService.deleteDocument(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PutMapping("/updateDocument/{id}")
	public ResponseEntity<String> updateDocument(@RequestBody Document doc, @PathVariable Integer id) {
		String result = this.documentService.updateDocument(doc,id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
