
package com.hrm.main.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class AddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_id_seq")
	@SequenceGenerator(name = "ad_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "ad_id_seq")
	private int adId;

	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kk")
	private PermanentAddress perAdd;

	@OneToOne(mappedBy = "addCur", cascade = CascadeType.ALL)
	@JoinColumn(name = "kk1")
	private PresentAddress preAdd;

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PermanentAddress getPerAdd() {
		return perAdd;
	}

	public void setPerAdd(PermanentAddress perAdd) {
		this.perAdd = perAdd;
	}

	public PresentAddress getPreAdd() {
		return preAdd;
	}

	public void setPreAdd(PresentAddress preAdd) {
		this.preAdd = preAdd;
	}

}
