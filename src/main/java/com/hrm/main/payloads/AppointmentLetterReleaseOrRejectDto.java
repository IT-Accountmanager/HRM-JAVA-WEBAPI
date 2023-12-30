package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;

public class AppointmentLetterReleaseOrRejectDto {

	private EmployeeStatus employeeStatus;

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

}
