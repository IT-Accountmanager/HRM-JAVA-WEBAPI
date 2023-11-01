
package com.hrm.main.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Profile_seq")
	@SequenceGenerator(name = "Profile_seq", initialValue = 1, allocationSize = 1, sequenceName = "Profile_seq")

	private int profileId;

	/*
	 * @JsonIgnore
	 * 
	 * @OneToOne(mappedBy = "candidateId") private Onboarding onboarding;
	 */
	// private Agreement agreement;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "personalId")
	private Personal personal;

	/*
	 * @OneToMany(mappedBy = "profile") //@JoinColumn(name = "familyId") private
	 * Set<Family> family;
	 */
	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "educationId")
	private Education education;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "workId")
	private Work work;

	/*
	 * public Onboarding getOnboarding() { return onboarding; }
	 * 
	 * public void setOnboarding(Onboarding onboarding) { this.onboarding =
	 * onboarding; }
	 */

	// -----------------------------------------
	private int candidateId;

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	// -----------------------------------------
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	/*
	 * public Set<Family> getFamily() { return family; }
	 * 
	 * public void setFamily(Set<Family> family) { this.family = family; }
	 */
	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

}
