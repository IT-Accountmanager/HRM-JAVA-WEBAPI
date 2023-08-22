
package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class PresentAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pre_id_seq")
	@SequenceGenerator(name = "pre_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "pre_id_seq")
	private int preAddId;
	private String country;
	private String state;
	private String city;
	private String houseNo;
	private String area;
	private String landmark;
	private String pincode;

	/*
	 * @OneToOne //@JoinColumn(name = "2kk") private AddressDetails
	 * presentAddressDetails;
	 */
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "Present_Id", referencedColumnName = "id") private
	 * PersonalDetails current;
	 * 
	 * public PersonalDetails getCurrent() { return current; }
	 * 
	 * public void setCurrent(PersonalDetails current) { this.current = current; }
	 */

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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getPreAddId() {
		return preAddId;
	}

	public void setPreAddId(int preAddId) {
		this.preAddId = preAddId;
	}

	/*
	 * public AddressDetails getPresentAddressDetails() { return
	 * presentAddressDetails; }
	 * 
	 * public void setPresentAddressDetails(AddressDetails presentAddressDetails) {
	 * this.presentAddressDetails = presentAddressDetails; }
	 */
	

}
