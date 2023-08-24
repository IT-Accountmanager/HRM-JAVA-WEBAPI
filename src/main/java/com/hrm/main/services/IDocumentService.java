package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Document;

public interface IDocumentService {

	String createDocument(Document doc);

	List<Document> getAllDocument();

	Document getDocument(Integer id);

	String deleteDocument(Integer id);

	String updateDocument(Document doc, Integer id);

	

}
