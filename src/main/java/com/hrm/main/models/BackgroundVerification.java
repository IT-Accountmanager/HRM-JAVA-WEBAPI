package com.hrm.main.models;

import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BGV")
public class BackgroundVerification {
	@Id
	private long candidateId;
	private boolean check1;
	private boolean check2;
	private boolean check3;
	private boolean check4;
	private boolean check5;
	private boolean check6;
	private boolean check7;
	private boolean check8;
	private boolean check9;
	private boolean check10;

	private DetailsSubmissionStatus backgroundVerificationSubmissionStatus = getBackgroundVerificationSubmissionStatus().Pending;

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public boolean isCheck1() {
		return check1;
	}

	public void setCheck1(boolean check1) {
		this.check1 = check1;
	}

	public boolean isCheck2() {
		return check2;
	}

	public void setCheck2(boolean check2) {
		this.check2 = check2;
	}

	public boolean isCheck3() {
		return check3;
	}

	public void setCheck3(boolean check3) {
		this.check3 = check3;
	}

	public boolean isCheck4() {
		return check4;
	}

	public void setCheck4(boolean check4) {
		this.check4 = check4;
	}

	public boolean isCheck5() {
		return check5;
	}

	public void setCheck5(boolean check5) {
		this.check5 = check5;
	}

	public boolean isCheck6() {
		return check6;
	}

	public void setCheck6(boolean check6) {
		this.check6 = check6;
	}

	public boolean isCheck7() {
		return check7;
	}

	public void setCheck7(boolean check7) {
		this.check7 = check7;
	}

	public boolean isCheck8() {
		return check8;
	}

	public void setCheck8(boolean check8) {
		this.check8 = check8;
	}

	public boolean isCheck9() {
		return check9;
	}

	public void setCheck9(boolean check9) {
		this.check9 = check9;
	}

	public boolean isCheck10() {
		return check10;
	}

	public void setCheck10(boolean check10) {
		this.check10 = check10;
	}

	public DetailsSubmissionStatus getBackgroundVerificationSubmissionStatus() {
		return backgroundVerificationSubmissionStatus;
	}

	public void setBackgroundVerificationSubmissionStatus(
			DetailsSubmissionStatus backgroundVerificationSubmissionStatus) {
		this.backgroundVerificationSubmissionStatus = backgroundVerificationSubmissionStatus;
	}

}
