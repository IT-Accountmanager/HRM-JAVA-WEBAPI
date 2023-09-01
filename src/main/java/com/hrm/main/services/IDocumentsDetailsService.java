
package com.hrm.main.services;

import com.hrm.main.models.DocumentDetails;

public interface IDocumentsDetailsService {

	String addDocuments(DocumentDetails docDetails);

	DocumentDetails getDocumentsDetById(Integer docId);

}
