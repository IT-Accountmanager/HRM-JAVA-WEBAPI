package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.DocumentDetails;
import com.hrm.main.repositories.IDocumentsDetailsRepository;
import com.hrm.main.services.IDocumentsDetailsService;

@Service
public class DocumentsDetailsServiceImpl implements IDocumentsDetailsService {

	@Autowired
	IDocumentsDetailsRepository docRepo;

	@Override
	public String addDocuments(DocumentDetails docDetails) {
		try {
			var save = this.docRepo.save(docDetails);
			if (save.getDocId() > 0) {
				return "Documents Details are added successfully.";
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Documents Details are not added.";
	}

}
