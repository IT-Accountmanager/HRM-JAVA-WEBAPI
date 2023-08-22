
package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.DocumentDetails;

public interface IDocumentsDetailsService {

	String addDocuments(DocumentDetails docDetails);

	List<DocumentDetails> getDocumentsDetById(Integer docId);

}
