package com.hrm.main.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class DocumentUpload {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_upload_seq")
	@SequenceGenerator(name = "doc_upload_seq", allocationSize = 1, initialValue = 1, sequenceName = "doc_upload_seq")
	private int upId;
	private String fileName;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "file64", columnDefinition = "LONGBLOB")
	private byte[] content;
	private String documentType;
	@Transient
	public String base64Data;

	/*
	 * @ManyToOne //@JoinColumn(name = "docDetailsId") private DocumentDetails
	 * docDetails;
	 */
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

	/*
	 * public DocumentDetails getDocDetails() { return docDetails; }
	 * 
	 * public void setDocDetails(DocumentDetails docDetails) { this.docDetails =
	 * docDetails; }
	 */

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public DocumentUpload(int upId, String fileName, byte[] content, String documentType) {
		super();
		this.upId = upId;
		this.fileName = fileName;
		this.content = content;
		this.documentType = documentType;
	}

	public DocumentUpload() {
		super();
	}

}
