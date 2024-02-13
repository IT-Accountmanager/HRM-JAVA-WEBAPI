package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.Half1;
import com.hrm.main.models.Helper.EnumCollection.Half2;
import com.hrm.main.models.Helper.EnumCollection.LeaveType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Leavetable")

public class ApplyLeave_Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sno;
	private LeaveType leaveType;
	
	@Column(unique = true)
	private LocalDate startDate;
	
	@Column(unique = true)
	private LocalDate endDate;
	private Half1 half1;
	private Half2 half2;
	private String reason;
	
	
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Half1 getHalf1() {
		return half1;
	}
	public void setHalf1(Half1 half1) {
		this.half1 = half1;
	}
	public Half2 getHalf2() {
		return half2;
	}
	public void setHalf2(Half2 half2) {
		this.half2 = half2;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	public ApplyLeave_Entity(Long sno, LeaveType leaveType, LocalDate startDate, LocalDate endDate, Half1 half1,
			Half2 half2, String reason) {
		super();
		this.sno = sno;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.half1 = half1;
		this.half2 = half2;
		this.reason = reason;
	}
	
	
	public ApplyLeave_Entity() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return "ApplyLeave_Entity [sno=" + sno + ", leaveType=" + leaveType + ", startDate=" + startDate + ", endDate="
				+ endDate + ", half1=" + half1 + ", half2=" + half2 + ", reason=" + reason + ", getSno()=" + getSno()
				+ ", getLeaveType()=" + getLeaveType() + ", getStartDate()=" + getStartDate() + ", getEndDate()="
				+ getEndDate() + ", getHalf1()=" + getHalf1() + ", getHalf2()=" + getHalf2() + ", getReason()="
				+ getReason() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
