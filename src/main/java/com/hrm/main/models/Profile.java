
package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Profile_seq")
	@SequenceGenerator(name = "Profile_seq", initialValue = 1, allocationSize = 1, sequenceName = "Profile_seq")
	private int profileId;

	@OneToOne
	@JoinColumn(name = "profile")
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

}
