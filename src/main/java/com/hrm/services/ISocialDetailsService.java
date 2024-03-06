package com.hrm.services;

import com.hrm.models.SocialDetails;

public interface ISocialDetailsService {

	String addSocialDetails(SocialDetails socialDetails);

	SocialDetails getSocialServiceById(Integer id);

}
