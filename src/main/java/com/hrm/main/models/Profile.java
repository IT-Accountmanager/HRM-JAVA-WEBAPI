
package com.hrm.main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore

	@OneToOne(mappedBy = "profile")
	private Onboarding onboarding;

	public Onboarding getOnboarding() {
		return onboarding;
	}

	public void setOnboarding(Onboarding onboarding) {
		this.onboarding = onboarding;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	// private Agreement agreement;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "personalId")
	private Personal personal;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "familyId")
	private Family family;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "educationId")
	private Education education;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "workId")
	private Work work;

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

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
