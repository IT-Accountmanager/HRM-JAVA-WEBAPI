package com.hrm.main.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class SocialDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sd_id_seq")
	@SequenceGenerator(name = "sd_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "sd_id_seq")
	@Column(name = "id")
	private int socialDetailsId;
	private String linkedin;
	private String facebook;
	private String instagram;

	public int getSocialDetailsId() {
		return socialDetailsId;
	}

	public void setSocialDetailsId(int socialDetailsId) {
		this.socialDetailsId = socialDetailsId;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

}
