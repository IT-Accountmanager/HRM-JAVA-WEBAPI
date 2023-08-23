package com.hrm.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrm.main.models.Document;


public interface IDocumentService {

	String createDocument(Document doc);

	List<Document> getAllDocument();

}
