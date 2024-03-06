package com.hrm.payloads;

public class AgreementEditDto {

	private String chequeNo1;
	private String chequeNo2;
	private byte[] sign;
	public String signBase64;
	private byte[] leftHandThumbImpression;
	public String leftHandThumbImpressionBase64;

	public String getChequeNo1() {
		return chequeNo1;
	}

	public void setChequeNo1(String chequeNo1) {
		this.chequeNo1 = chequeNo1;
	}

	public String getChequeNo2() {
		return chequeNo2;
	}

	public void setChequeNo2(String chequeNo2) {
		this.chequeNo2 = chequeNo2;
	}

	public byte[] getSign() {
		return sign;
	}

	public void setSign(byte[] sign) {
		this.sign = sign;
	}

	public byte[] getLeftHandThumbImpression() {
		return leftHandThumbImpression;
	}

	public void setLeftHandThumbImpression(byte[] leftHandThumbImpression) {
		this.leftHandThumbImpression = leftHandThumbImpression;
	}

}
