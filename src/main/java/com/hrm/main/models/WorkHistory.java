package com.hrm.main.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class WorkHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_his_id_seq")
	@SequenceGenerator(name = "work_his_id_seq", allocationSize = 1, initialValue = 1, sequenceName = "work_his_id_seq")
	private int deptId;
	private String department;
	private String designation;
	private String workFrom;
	private String workUpto;

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getWorkFrom() {
		return workFrom;
	}

	public void setWorkFrom(String workFrom) {
		this.workFrom = workFrom;
	}

	public String getWorkUpto() {
		return workUpto;
	}

	public void setWorkUpto(String workUpto) {
		this.workUpto = workUpto;
	}

	

}
