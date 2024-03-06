
package com.hrm.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.AddressDetails;
import com.hrm.repositories.AddressDetailsRepository;
import com.hrm.services.IAddressDetailsService;

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
