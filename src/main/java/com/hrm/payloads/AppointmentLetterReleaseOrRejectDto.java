package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.EmployeeStatus;

public class AppointmentLetterReleaseOrRejectDto {

	private EmployeeStatus employeeStatus;

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

}
