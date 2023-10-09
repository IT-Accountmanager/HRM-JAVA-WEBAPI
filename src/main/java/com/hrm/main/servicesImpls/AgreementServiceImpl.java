package com.hrm.main.servicesImpls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Agreement;
import com.hrm.main.repositories.IAgreementRepository;
import com.hrm.main.services.IAgreementService;

@Service

public class AgreementServiceImpl implements IAgreementService {
	
	@Autowired
	private IAgreementRepository agreementRepository;

	@Override
	public String createAgreement(Agreement agreement) {
		try {
			var agree = this.agreementRepository.save(agreement);
			if(agree.getId()>0) {
				return "Agreement details are added " + agree.getId();
			}	
			
		} catch (Exception e) {
			e.getMessage();
		}
		return "Agreement details are not added ";
	}

	@Override
	public List<Agreement> getAllAgreement() {
		List<Agreement> allAgreement=agreementRepository.findAll();
		return allAgreement;
	}

	@Override
	public Agreement getAgreementById(int id) {
		Agreement agree = agreementRepository.findById(id).get();
		return agree;
	}

	@Override
	public String updateAgreement(Agreement agreement, Integer id) {
		try {
			
			if (this.agreementRepository.existsById(id)) {
				
				agreement.setId(id);
				
				this.agreementRepository.save(agreement);
				
				return "Id no. " + id +" is updated";		
				
			}else {
				
				return "Id no. " + id +" is does not exists ";				
			}
			
		} catch (Exception e) {
			
			e.getMessage();
		}
		return "Id no. " +id+ " is not updated";
	}

	@Override
	public String deleteAgreement(Integer id) {
		try {
			
			agreementRepository.deleteById(id);
			
			return "Id no. " + id + " is deleted successfully.";
			
		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. "+ id + " is not deleted";
	}

}
