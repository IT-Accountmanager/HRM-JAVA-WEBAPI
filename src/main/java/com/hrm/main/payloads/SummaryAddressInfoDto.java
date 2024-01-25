package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.HouseType;

public class SummaryAddressInfoDto {

	private String permanentAddress;
	private HouseType permanentHouseType;
	private LocalDate permanentAddressSince;
	private String permanentPincode;
	private String presentAddress;
	private HouseType presentHouseType;
	private LocalDate presentAddressSince;
	private String presentPincode;

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public HouseType getPermanentHouseType() {
		return permanentHouseType;
	}

	public void setPermanentHouseType(HouseType permanentHouseType) {
		this.permanentHouseType = permanentHouseType;
	}

	public LocalDate getPermanentAddressSince() {
		return permanentAddressSince;
	}

	public void setPermanentAddressSince(LocalDate permanentAddressSince) {
		this.permanentAddressSince = permanentAddressSince;
	}

	public String getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public HouseType getPresentHouseType() {
		return presentHouseType;
	}

	public void setPresentHouseType(HouseType presentHouseType) {
		this.presentHouseType = presentHouseType;
	}

	public LocalDate getPresentAddressSince() {
		return presentAddressSince;
	}

	public void setPresentAddressSince(LocalDate presentAddressSince) {
		this.presentAddressSince = presentAddressSince;
	}

	public String getPresentPincode() {
		return presentPincode;
	}

	public void setPresentPincode(String presentPincode) {
		this.presentPincode = presentPincode;
	}

}
