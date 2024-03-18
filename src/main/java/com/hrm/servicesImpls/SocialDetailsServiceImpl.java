package com.hrm.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.SocialDetails;
import com.hrm.repositories.ISocialDetailsRepository;
import com.hrm.services.ISocialDetailsService;

@Service
public class SocialDetailsServiceImpl implements ISocialDetailsService {
	@Autowired
	ISocialDetailsRepository socialDetailsRepo;

	@Override
	public String addSocialDetails(SocialDetails socialDetails) {
		var addedSocial = this.socialDetailsRepo.save(socialDetails);
		try {
			if (addedSocial.getSocialDetailsId() > 0) {
				return "Social Details Added Successfully of Id : " + addedSocial.getSocialDetailsId();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Social Details Are Not Added ";
	}

	@Override
	public SocialDetails getSocialServiceById(Integer id) {
		SocialDetails socialDetails = socialDetailsRepo.findById(id).orElseThrow(null);
		return socialDetails;
	}

}