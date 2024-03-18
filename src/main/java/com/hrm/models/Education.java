package com.hrm.models;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;

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
	private String percentageOrGrade;
	private String collegeNameOrUniversity;
	private String eduPhoneNo;
	private String eduEmailId;
	@Column(name = "candidate_id")
	private long candidateId;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] certificate;
	@Transient
	public String base64Data;
	private DetailsSubmissionStatus educationSubmissionStatus = DetailsSubmissionStatus.Pending;

	private ApprovalStatus hrExecutiveApprovalStatus = ApprovalStatus.Pending;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus = ApprovalStatus.Pending;
	private String hrManagerRemark;

	public Education() {
		super();
	}

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

	public String getPercentageOrGrade() {
		return percentageOrGrade;
	}

	public void setPercentageOrGrade(String percentageOrGrade) {
		this.percentageOrGrade = percentageOrGrade;
	}

	public String getEduPhoneNo() {
		return eduPhoneNo;
	}

	public void setEduPhoneNo(String eduPhoneNo) {
		this.eduPhoneNo = eduPhoneNo;
	}

	public String getEduEmailId() {
		return eduEmailId;
	}

	public void setEduEmailId(String eduEmailId) {
		this.eduEmailId = eduEmailId;
	}

	public String getCollegeNameOrUniversity() {
		return collegeNameOrUniversity;
	}

	public void setCollegeNameOrUniversity(String collegeNameOrUniversity) {
		this.collegeNameOrUniversity = collegeNameOrUniversity;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getEducationSubmissionStatus() {
		return educationSubmissionStatus;
	}

	public void setEducationSubmissionStatus(DetailsSubmissionStatus educationSubmissionStatus) {
		this.educationSubmissionStatus = educationSubmissionStatus;
	}

	public ApprovalStatus getHrExecutiveApprovalStatus() {
		return hrExecutiveApprovalStatus;
	}

	public void setHrExecutiveApprovalStatus(ApprovalStatus hrExecutiveApprovalStatus) {
		this.hrExecutiveApprovalStatus = hrExecutiveApprovalStatus;
	}

	public String getHrExecutiveRemark() {
		return hrExecutiveRemark;
	}

	public void setHrExecutiveRemark(String hrExecutiveRemark) {
		this.hrExecutiveRemark = hrExecutiveRemark;
	}

	public ApprovalStatus getHrManagerApprovalStatus() {
		return hrManagerApprovalStatus;
	}

	public void setHrManagerApprovalStatus(ApprovalStatus hrManagerApprovalStatus) {
		this.hrManagerApprovalStatus = hrManagerApprovalStatus;
	}

	public String getHrManagerRemark() {
		return hrManagerRemark;
	}

	public void setHrManagerRemark(String hrManagerRemark) {
		this.hrManagerRemark = hrManagerRemark;
	}

}