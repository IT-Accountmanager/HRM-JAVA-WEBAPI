package com.hrm.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.DocumentDetails;
import com.hrm.models.DocumentUpload;
import com.hrm.repositories.DocumentUploadRepository;
import com.hrm.repositories.IDocumentsDetailsRepository;
import com.hrm.services.IDocumentsDetailsService;

@Service
public class DocumentsDetailsServiceImpl implements IDocumentsDetailsService {

	@Autowired
	IDocumentsDetailsRepository docRepo;
	@Autowired
	DocumentUploadRepository docUpRepo;

	@Override
	public String addDocuments(DocumentDetails docDetails) {

		try {

			for (DocumentUpload doc : docDetails.getDocuUpload()) {
				Decoder decoder = Base64.getDecoder();
				// Ensure that the length is a multiple of 4
				while (doc.base64Data.length() % 4 != 0) {
					doc.base64Data += "=";
				}

				doc.setContent(decoder.decode(doc.base64Data));

			}

			var save = this.docRepo.save(docDetails);
			/*
			 * docUpload.setDocDetails(docDetails); docUpRepo.save(docUpload);
			 */
			if (save.getDocId() > 0) {

				return "Documents Details are added successfully.";
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Documents Details are not added.";
	}

	@Override
	public DocumentDetails getDocumentsDetById(Integer docId) {
		DocumentDetails document = docRepo.findById(docId).get();
		return document;
	}

}