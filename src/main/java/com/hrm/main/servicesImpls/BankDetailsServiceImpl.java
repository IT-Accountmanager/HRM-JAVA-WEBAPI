package com.hrm.main.servicesImpls;

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
		var details = this.bankDetailsRepo.save(bankDetails);
		try {
			if (details.getBankDetId() > 0) {
				return "Bank details successfully added of Id : " + details.getBankDetId();
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Bank details not added of Id : " + details.getBankDetId();
	}

}
