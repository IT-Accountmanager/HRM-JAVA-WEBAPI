package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.SocialDetails;
import com.hrm.main.repositories.ISocialDetailsRepository;
import com.hrm.main.services.ISocialDetailsService;

@Service
public class SocialDetailsServiceImpl implements ISocialDetailsService {
	@Autowired
	ISocialDetailsRepository socialDetailsRepo;

	@Override
	public String addSocialDetails(SocialDetails socialDetails) {
		var addedSocial = this.socialDetailsRepo.save(socialDetails);
		try {
			if (addedSocial.getSocialDetailsId() > 1) {
				return "Social Details Added Successfully of Id : " + addedSocial.getSocialDetailsId();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Social Details Are Not Added ";
	}

}
