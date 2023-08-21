
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
public class PermanentAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "per_id_seq")
	@SequenceGenerator(name = "per_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "per_id_seq")
	private int perAddId;
	private String country;
	private String state;
	private String city;
	private String houseNo;
	private String area;
	private String landmark;
	private String pincode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "1kk")
	private AddressDetails addPer;

	/* @OneToOne */

	/*
	 * @JoinColumn(name = "personal_details_id", referencedColumnName = "id")
	 * private PersonalDetails permanent;
	 */
	/*
	 * public PersonalDetails getPermanent() { return permanent; }
	 * 
	 * public void setPermanent(PersonalDetails permanent) { this.permanent =
	 * permanent;
	 * 
	 * }
	 */

	public int getPerAddId() {
		return perAddId;
	}

	public void setPerAddId(int perAddId) {
		this.perAddId = perAddId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public AddressDetails getAddPer() {
		return addPer;
	}

	public void setAddPer(AddressDetails addPer) {
		this.addPer = addPer;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
