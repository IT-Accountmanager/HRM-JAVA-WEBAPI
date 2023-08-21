package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DocumentUpload {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int upId;
	private String fileName;
	private String content;
	@ManyToOne
	//@JoinColumn(name = "docDetailsId")
	private DocumentDetails docDetails;

	public int getUpId() {
		return upId;
	}

	public void setUpId(int upId) {
		this.upId = upId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DocumentDetails getDocDetails() {
		return docDetails;
	}

	public void setDocDetails(DocumentDetails docDetails) {
		this.docDetails = docDetails;
	}

	public DocumentUpload(int upId, String fileName, String content, DocumentDetails docDetails) {
		super();
		this.upId = upId;
		this.fileName = fileName;
		this.content = content;
		this.docDetails = docDetails;
	}

	public DocumentUpload() {
		super();
	}

	@Override
	public String toString() {
		return "DocumentUpload [upId=" + upId + ", fileName=" + fileName + ", content=" + content + ", docDetails="
				+ docDetails + "]";
	}

}
