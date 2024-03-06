
package com.hrm.services;

import com.hrm.models.DocumentDetails;

public interface IDocumentsDetailsService {

	String addDocuments(DocumentDetails docDetails);

	DocumentDetails getDocumentsDetById(Integer docId);

}
