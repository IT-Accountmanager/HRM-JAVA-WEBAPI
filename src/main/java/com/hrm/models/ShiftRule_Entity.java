package com.hrm.models;


import com.hrm.Helper.EnumCollection.ShiftRule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table

public class ShiftRule_Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sno;
	private ShiftRule shiftRule;
	
	
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public ShiftRule getShiftRule() {
		return shiftRule;
	}
	public void setShiftRule(ShiftRule shiftRule) {
		this.shiftRule = shiftRule;
	}
	
	

}
