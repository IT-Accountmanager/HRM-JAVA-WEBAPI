package com.hrm.main.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public class AddressDetails {

	@OneToOne(cascade = CascadeType.ALL)
	private PresentAddress presentAddress;
	@OneToOne(cascade = CascadeType.ALL)
	private PermanentAddress permanentAddress;

}
