package com.hrm.main.models;

import java.time.LocalDate;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "education")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Education_Id_Sequence")
	@SequenceGenerator(name = "Education_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Education_Id_Sequence")
	private int educationId;
	private String qualification;
	private String courseType;
	private String stream;
	private LocalDate startDate;
	private LocalDate endDate;
	private float percentage;
	private String collegeName;

	@Column(name = "candidate_id")
	private String candidateId;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] certificate;
	@Transient
	public String base64Data;
	private DetailsSubmissionStatus educationSubmissionStatus = DetailsSubmissionStatus.pending;

	public byte[] getCertificate() {
		return certificate;
	}

	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}

	public int getEducationId() {
		return educationId;
	}

	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getEducationSubmissionStatus() {
		return educationSubmissionStatus;
	}

	public void setEducationSubmissionStatus(DetailsSubmissionStatus educationSubmissionStatus) {
		this.educationSubmissionStatus = educationSubmissionStatus;
	}

}
