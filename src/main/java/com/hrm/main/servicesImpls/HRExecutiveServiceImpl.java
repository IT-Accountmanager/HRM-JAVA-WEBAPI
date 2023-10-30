package com.hrm.main.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Education;
import com.hrm.main.models.Family;
import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.main.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrExecutiveWorkApprovalDto;
import com.hrm.main.repositories.IEducationRepository;
import com.hrm.main.repositories.IFamilyRepository;
import com.hrm.main.repositories.IHRExecutiveRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.IHRExecutiveService;

@Service

public class HRExecutiveServiceImpl implements IHRExecutiveService {

	@Autowired
	private IHRExecutiveRepository hRExecutiveRepository;
	@Autowired
	private IOnboardingRepository onboardingRepository;
	@Autowired
	private IPersonalRepository personalRepository;
	@Autowired
	private IFamilyRepository familyRepository;
	@Autowired
	private IEducationRepository educationRepository;
	@Autowired
	private IWorkRepository workRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String createExecutive(HRExecutive hrExecutive) {
		try {
			// super()

			var executive1 = this.hRExecutiveRepository.save(hrExecutive);
			if (executive1.getId() > 0) {
				return "All details are added : " + executive1.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "All details are not added : ";
	}

	@Override
	public List<Onboarding> getAllExecutive(CandidatesStatus status) {
		List<Onboarding> allExecutive = this.onboardingRepository.findAllByCandidatesStatus(status);
		return allExecutive;
	}

	@Override
	public HRExecutive getExecutiveById(int id) {
		HRExecutive hr = hRExecutiveRepository.findById(id).get();
		return hr;
	}

	@Override
	public String updateHRExecutive(HRExecutive hrExecutive, Integer id) {
		try {

			if (this.hRExecutiveRepository.existsById(id)) {

				hrExecutive.setId(id);

				this.hRExecutiveRepository.save(hrExecutive);

				return "Id no. : " + id + " is updated.";

			} else {
				return "Id no. : " + id + " is does not exists";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. : " + id + "is not updated.";
	}

	@Override
	public String deleteHrExecutive(Integer id) {
		try {
			hRExecutiveRepository.deleteById(id);

			return "Id no. : " + id + " is deleted";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. : " + id + " is not deleted";
	}

	/*
	 * @Override public String tranferProfileToHRExecutive() { List<Onboarding>
	 * findAll = this.iOnboardingRepository.findAll(); List<HRExecutive>
	 * listHrExecutive = new ArrayList<>();
	 * 
	 * for (Onboarding onboarding : findAll) { int candidateId =
	 * onboarding.getCandidateId(); List<Personal> candidates =
	 * this.iPersonalRepository.findByCandidateId(candidateId);
	 * 
	 * // Check if there are multiple candidates with the same candidateId. if
	 * (candidates.size() > 1) { // Handle the case where multiple candidates have
	 * the same candidateId. // You can log an error or decide how to handle this
	 * situation. // For now, we'll skip the candidate. continue; }
	 * 
	 * // Check if there is a candidate with the given candidateId. if
	 * (!candidates.isEmpty()) { Personal candidate = candidates.get(0); // Assuming
	 * candidateId is unique. HRExecutive transfer = new HRExecutive();
	 * 
	 * transfer.setJobTitle(onboarding.getJobTitle());
	 * transfer.setCandidateId(candidateId);
	 * transfer.setCandidateName(onboarding.getCandidateName());
	 * transfer.setContactNumber(onboarding.getContactNumber());
	 * transfer.setEmailId(onboarding.getEmailId());
	 * transfer.setBondPeriod(onboarding.getBondPeriod());
	 * transfer.setBondBreakAmount(onboarding.getBondBreakAmount());
	 * transfer.setCtc(onboarding.getCtc());
	 * 
	 * listHrExecutive.add(transfer); } }
	 * 
	 * if (!listHrExecutive.isEmpty()) {
	 * this.hRExecutiveRepository.saveAll(listHrExecutive); return
	 * "Transferred to HR Executive Successfully"; }
	 * 
	 * return "Not Transferred to HR Executive";
	 * 
	 * 
	 */

	// ---------------------------------------------------------------

	/*
	 * List<Onboarding> findAll = this.iOnboardingRepository.findAll();
	 * 
	 * List<HRExecutive> listHrExecutive = new ArrayList<>(); //
	 * System.out.println("----Find All----" + findAll);
	 * 
	 * for (Onboarding onboarding : findAll) { //
	 * System.out.println("----Onboarding----" + onboarding); String candidateId =
	 * onboarding.getCandidateId(); // System.out.println("----Candidate Id----" +
	 * // candidateId);
	 * 
	 * // System.out.println("----Print----" + // //
	 * this.iPersonalRepository.findByCandidateId(candidateId));
	 * 
	 * Personal candidate = this.iPersonalRepository.findByCandidateId(candidateId);
	 * System.out.println("----candidate----" + candidate); // if (candidate != null
	 * && candidate.getPersonalId() != 0) if (candidate != null) {
	 * 
	 * HRExecutive transfer = new HRExecutive();
	 * 
	 * transfer.setJobTitle(onboarding.getJobTitle());
	 * transfer.setCandidateId(candidateId);
	 * transfer.setCandidateName(onboarding.getCandidateName());
	 * transfer.setContactNumber(onboarding.getContactNumber());
	 * transfer.setEmailId(onboarding.getEmailId());
	 * transfer.setBondPeriod(onboarding.getBondPeriod());
	 * transfer.setBondBreakAmount(onboarding.getBondBreakAmount());
	 * transfer.setCtc(onboarding.getCtc()); // //
	 * transfer.setStatus(onboarding.getStatus());
	 * 
	 * listHrExecutive.add(transfer); } }
	 * 
	 * if (!listHrExecutive.isEmpty()) {
	 * this.hRExecutiveRepository.saveAll(listHrExecutive); return
	 * "Transfered to HR Executive Succesfully"; } return
	 * "Not Transfered to HR Executive"; }
	 */

	// ---------------------------------------------------------------------------------

	/*
	 * Personal personal = this.iPersonalRepository.findById(id).get(); if
	 * (!(personal.equals(null))) { HRExecutive transfer = new HRExecutive();
	 * 
	 * transfer.setCandidateId(personal.getCandidateId());
	 * transfer.setJobTitle(personal.g); this.hRExecutiveRepository.save(transfer);
	 * } return null;
	 */

	// -----------------------------------------------------------------

	/*
	 * @Override public List<Onboarding> getAllOnboarding() { try { List<Onboarding>
	 * allOnboarding = this.hRExecutiveRepository.getAllOnboarding(); return
	 * allOnboarding; } catch (Exception e) { return null; }
	 * 
	 * }
	 */

//--------------------------------------------------------------------------------------------------------------

	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Override public List<Onboarding> getAllOnboarding() { Configuration config =
	 * new Configuration(); config.configure(); // local SessionFactory bean created
	 * SessionFactory sessionFactory = config.buildSessionFactory(); // Session //
	 * session = sessionFactory.getCurrentSession();
	 * 
	 * Session session = sessionFactory.openSession(); session.beginTransaction();
	 * String sql = String.format(
	 * "SELECT o.candidate_name FROM `actus-web-api`.onboarding o left join personal p on o.candidate_id =p.candidate_id \\r\\n\"\r\n"
	 * +
	 * "			+ \"left join family f on o.candidate_id = f.candidate_id \\r\\n\"\r\n"
	 * +
	 * "			+ \"left join education e on o.candidate_id = e.candidate_id\\r\\n\"\r\n"
	 * +
	 * "			+ \"left join work w on o.candidate_id = w.candidate_id\\r\\n\"\r\n"
	 * +
	 * "			+ \"where p.status=1 and f.status =1 and e.status=1 and w.status=1"
	 * ); List<Onboarding> resultList = session.createNativeQuery(sql,
	 * Onboarding.class).getResultList(); session.getTransaction().commit();
	 * session.close(); return resultList; }
	 */

	// ---------------------------------------------------------------------------------------------------------------

	/*
	 * @Override public List<Onboarding> getAllOnboarding(CandidatesStatus status) {
	 * List<Onboarding> findAll =
	 * this.iOnboardingRepository.findAllByStatus(status); return findAll; }
	 */

	@Override
	public boolean postCandidateInHrExecutive(CandidatesStatus status) {
		List<Onboarding> findAllByCandidatesStatus = this.onboardingRepository.findAllByCandidatesStatus(status);
		boolean verify = false;
		// int id = 1;
		List<HRExecutive> hrExecutiveList = new ArrayList<>();

		for (Onboarding onboarding : findAllByCandidatesStatus) {

			HRExecutive existingExecutive = this.hRExecutiveRepository.findByCandidateId(onboarding.getCandidateId());

			if (existingExecutive == null) {
				HRExecutive executive = new HRExecutive();
				// executive.setId(id);
				executive.setJobTitle(onboarding.getJobTitle());
				executive.setCandidateId(onboarding.getCandidateId());
				executive.setCandidateName(onboarding.getCandidateName());
				executive.setContactNumber(onboarding.getContactNumber());
				executive.setEmailId(onboarding.getEmailId());
				executive.setBondPeriod(onboarding.getBondPeriod());
				executive.setBondBreakAmount(onboarding.getBondBreakAmount());
				executive.setCtc(onboarding.getCtc());
				executive.setStatus(onboarding.getCandidatesStatus());

				hrExecutiveList.add(executive);
				// id++;
				System.out.println(executive.getCandidateId());
			}
		}
		List<HRExecutive> success = this.hRExecutiveRepository.saveAll(hrExecutiveList);

		if (success.size() > 0) {
			verify = true;
		}

		return verify;
	}

	/*
	 * Personal personal = this.personalRepository.findByCandidateId(candidateId);
	 * 
	 * HrExecutivePersonalDto hrExecutivePersonalDto2 =
	 * this.modelMapper.map(personal, HrExecutivePersonalDto.class);
	 * 
	 * return modelMapper.map(hrExecutive, HrExecutivePersonalDto.class);
	 */

	@Override
	public HrExecutivePersonalApprovalDto personalApproval(HrExecutivePersonalApprovalDto hrExecutivePersonalDto,
			long candidateId) {
		Personal existingPersonal = personalRepository.findByCandidateId(candidateId);
		modelMapper.map(hrExecutivePersonalDto, existingPersonal);
		this.personalRepository.save(existingPersonal);
		return hrExecutivePersonalDto;
	}

	@Override
	public HrExecutiveEducationApprovalDto educationApproval(HrExecutiveEducationApprovalDto hrExecutiveEducationDto,
			long candidateId) {
		List<Education> existingEducation = this.educationRepository.findAllEducationByCandidateId(candidateId);

		modelMapper.map(hrExecutiveEducationDto, existingEducation);

		educationRepository.saveAll(existingEducation);
		return hrExecutiveEducationDto;
	}

	@Override
	public HrExecutiveWorkApprovalDto workApproval(HrExecutiveWorkApprovalDto hrExecutiveWorkDto, long candidateId) {
		Work exisitingWork = this.workRepository.findByCandidateId(candidateId);

		modelMapper.map(hrExecutiveWorkDto, exisitingWork);

		this.workRepository.save(exisitingWork);
		return hrExecutiveWorkDto;
	}

	@Override
	public HrExecutiveFamilyApprovalDto familyApproval(HrExecutiveFamilyApprovalDto hrExecutiveFamilyDto,
			long candidateId) {
		List<Family> existingFamily = this.familyRepository.findAllByCandidateId(candidateId);
		for (Family family : existingFamily) {
			modelMapper.map(hrExecutiveFamilyDto, family);
		}
		this.familyRepository.saveAll(existingFamily);
		return hrExecutiveFamilyDto;
	}

	@Override
	public HrExecutiveFamilyApprovalDto getFamilyApproval(long candidateId) {
		List<Family> list = this.familyRepository.findAllByCandidateId(candidateId);
		for (Family family : list) {
			HrExecutiveFamilyApprovalDto map = this.modelMapper.map(list, HrExecutiveFamilyApprovalDto.class);
		}
		return null;
	}

	@Override
	public Onboarding getByCandidateId(long candidateId) {
		Onboarding findByCandidateId = this.onboardingRepository.findByCandidateId(candidateId);
		return findByCandidateId;
	}

	@Override
	public HrExecutivePersonalApprovalDto getPersonalApproval(long candidateId) {
		Personal candidate = this.personalRepository.findByCandidateId(candidateId);
		HrExecutivePersonalApprovalDto map = this.modelMapper.map(candidate, HrExecutivePersonalApprovalDto.class);
		return map;
	}

	@Override
	public HrExecutiveEducationApprovalDto getEducationApproval(long candidateId) {
		Education education = this.educationRepository.findByCandidateId(candidateId);
		HrExecutiveEducationApprovalDto map = this.modelMapper.map(education, HrExecutiveEducationApprovalDto.class);
		return map;
	}

	@Override
	public HrExecutiveWorkApprovalDto getWorkApproval(long candidateId) {
		Work work = this.workRepository.findByCandidateId(candidateId);
		HrExecutiveWorkApprovalDto map = this.modelMapper.map(work, HrExecutiveWorkApprovalDto.class);
		return map;
	}

}
