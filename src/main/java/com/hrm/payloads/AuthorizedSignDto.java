package com.hrm.payloads;

public class AuthorizedSignDto {
	private byte[] authorisedSignature;
	public String base64Data;

	public byte[] getAuthorisedSignature() {
		return authorisedSignature;
	}

	public void setAuthorisedSignature(byte[] authorisedSignature) {
		this.authorisedSignature = authorisedSignature;
	}

}
