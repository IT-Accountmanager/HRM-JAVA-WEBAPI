package com.hrm.main.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.BankDetails;
import com.hrm.main.repositories.IBankDetailsRepository;
import com.hrm.main.services.IBankDetailsService;

@Service
public class BankDetailsServiceImpl implements IBankDetailsService {

	@Autowired
	IBankDetailsRepository bankDetailsRepo;

	@Override
	public String addDetails(BankDetails bankDetails) {

		try {
			// byte[] bankDoc = bankDetails.getBankDoc();
			Decoder decoder = Base64.getDecoder();

			while (bankDetails.base64Data.length() % 4 != 0) {
				bankDetails.base64Data += "=";
			}

			bankDetails.setBankDoc(decoder.decode(bankDetails.base64Data));

			BankDetails details = this.bankDetailsRepo.save(bankDetails);
			if (details.getBankDetId() > 0) {
				return "Bank details successfully added of Id : " + details.getBankDetId();
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Bank details are not added ";
	}

}
