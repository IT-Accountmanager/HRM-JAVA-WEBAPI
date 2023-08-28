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

	@Autowired
	WorkServiceImpl workServImpl;

	@Override
	public String createDocument(Document doc) {
		try {

			workServImpl.createWork(doc.getWork());
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

	@Override
	public Document getDocument(Integer id) {
		Document doc = documentRepo.findById(id).get();
		return doc;
	}

	@Override
	public String deleteDocument(Integer id) {
		try {
			documentRepo.deleteById(id);
			return "Id no. " + id + " is Deleted Succeefully.";
		} catch (Exception e) {
			e.getMessage();
		}

		return "Id no. " + id + " is not Deleted.";
	}

	@Override
	public String updateDocument(Document doc, Integer id) {
		try {
			doc.setId(id);

			documentRepo.save(doc);

			return "Id no. " + id + " Is Updated.";

		} catch (Exception ex) {
			ex.getMessage();
		}

		return "Id no. " + id + " Is not Updated.";

	}

}
