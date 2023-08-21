
package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.AddressDetails;
import com.hrm.main.repositories.AddressDetailsRepository;
import com.hrm.main.services.IAddressDetailsService;

@Service
public class AddressDetailsServiceImpl implements IAddressDetailsService {

	@Autowired
	AddressDetailsRepository addressDetailsRepo;

	@Override
	public String addAdd(AddressDetails addressDetails) {
		try {
			var save = this.addressDetailsRepo.save(addressDetails);

			if (save.getAdId() > 0) {

				return "Address Details are added successfully.";
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Address Details are not added.";
	}

}
