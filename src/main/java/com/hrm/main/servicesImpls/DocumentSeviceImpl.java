package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Document;
import com.hrm.main.repositories.IDocumentRepository;
import com.hrm.main.services.IDocumentService;

@Service
public class DocumentSeviceImpl implements IDocumentService {

	@Autowired
	private IDocumentRepository documentRepo;

	@Override
	public String createDocument(Document doc) {
		try {
			var document1 = this.documentRepo.save(doc);

			if (document1.getId() > 0) {
				return "Documents are added:" + document1.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Documents are not added";

	}

	@Override
	public List<Document> getAllDocument() {
		List<Document> allDoc = documentRepo.findAll();
		return allDoc;
	}

}
