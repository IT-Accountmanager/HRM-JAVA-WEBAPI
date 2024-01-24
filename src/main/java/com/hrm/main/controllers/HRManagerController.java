package com.hrm.main.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.CreateAppointmentLetterDto;
import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.payloads.AuthorizedSignDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.main.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrExecutiveWorkApprovalDto;
import com.hrm.main.payloads.HrManagerAgreementApprovalDto;
import com.hrm.main.payloads.HrManagerBackgroundVerificationDto;
import com.hrm.main.payloads.HrManagerDto;
import com.hrm.main.payloads.HrManagerEducationApprovalDto;
import com.hrm.main.payloads.HrManagerFamilyApprovalDto;
import com.hrm.main.payloads.HrManagerPersonalApprovalDto;
import com.hrm.main.payloads.HrManagerWorkApprovalDto;
import com.hrm.main.services.IHRExecutiveService;
import com.hrm.main.services.IHRManagerService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/HRManager")

public class HRManagerController {

	@Autowired
	IHRManagerService hRManagerService;

	// ----------------Get All For Hr Manager Page---------------
	@GetMapping("/getAll/{status}")
	public ResponseEntity<List<HrManagerDto>> getAllCandidates(@PathVariable CandidatesStatus status) {
		List<HrManagerDto> result = this.hRManagerService.getAllCandidates(status);
		return new ResponseEntity<List<HrManagerDto>>(result, HttpStatus.OK);
	}

	// ------------Get for view single candidate info---------------
	@GetMapping("/get/{candidateId}")
	public HrManagerDto getByCandidateId(@PathVariable long candidateId) {

		HrManagerDto candidate = this.hRManagerService.getCandidate(candidateId);

		return candidate;
	}

	// ------------ Personal Approval By Hr Manager-----------------
	// __________________POST____________________
	@PostMapping("personalApproval/{candidateId}")
	public ResponseEntity<HrManagerPersonalApprovalDto> personalApproval(
			@RequestBody HrManagerPersonalApprovalDto hrManagerPersonalApprovalDto, @PathVariable long candidateId) {
		HrManagerPersonalApprovalDto result = this.hRManagerService.personalApproval(hrManagerPersonalApprovalDto,
				candidateId);
		return new ResponseEntity<HrManagerPersonalApprovalDto>(result, HttpStatus.OK);
	}

	// ___________________GET____________________
	@GetMapping("getPersonalApproval/{candidateId}")
	public ResponseEntity<HrManagerPersonalApprovalDto> getPersonalApproval(@PathVariable long candidateId) {
		HrManagerPersonalApprovalDto result = this.hRManagerService.getPersonalApproval(candidateId);
		return new ResponseEntity<HrManagerPersonalApprovalDto>(result, HttpStatus.OK);
	}

	// ------------ Family Approval By Hr Manager-----------------
	// ________________________POST________________________________
	@PostMapping("familyApproval/{candidateId}")
	public ResponseEntity<HrManagerFamilyApprovalDto> familyApproval(
			@RequestBody HrManagerFamilyApprovalDto hrManagerFamilyApprovalDto, @PathVariable long candidateId) {
		HrManagerFamilyApprovalDto result = this.hRManagerService.familyApproval(hrManagerFamilyApprovalDto,
				candidateId);
		return new ResponseEntity<HrManagerFamilyApprovalDto>(result, HttpStatus.OK);
	}

	// ________________________GET_____________________________
	@GetMapping("getFamilyApproval/{candidateId}")
	public ResponseEntity<HrManagerFamilyApprovalDto> getFamilyApproval(@PathVariable long candidateId) {
		HrManagerFamilyApprovalDto result = this.hRManagerService.getFamilyApproval(candidateId);
		return new ResponseEntity<HrManagerFamilyApprovalDto>(result, HttpStatus.OK);
	}

	// ------------ Education Approval By Hr Manager-----------------
	// ________________________POST________________________________
	@PostMapping("educationApproval/{candidateId}")
	public ResponseEntity<HrManagerEducationApprovalDto> educationApproval(
			@RequestBody HrManagerEducationApprovalDto hrManagerEducationApprovalDto, @PathVariable long candidateId) {
		HrManagerEducationApprovalDto result = this.hRManagerService.educationApproval(hrManagerEducationApprovalDto,
				candidateId);
		return new ResponseEntity<HrManagerEducationApprovalDto>(result, HttpStatus.OK);
	}

	// ________________________GET____________________________
	@GetMapping("getEducationApproval/{candidateId}")
	public ResponseEntity<HrManagerEducationApprovalDto> getEducationApproval(@PathVariable long candidateId) {
		HrManagerEducationApprovalDto result = this.hRManagerService.getEducationApproval(candidateId);
		return new ResponseEntity<HrManagerEducationApprovalDto>(result, HttpStatus.OK);
	}

	// ------------ Work Approval By Hr Manager-----------------
	// ________________________POST________________________________
	@PostMapping("workApproval/{candidateId}")
	public ResponseEntity<Integer> workApproval(@RequestBody HrManagerWorkApprovalDto hrManagerWorkApprovalDto,
			@PathVariable long candidateId) {
		int result = this.hRManagerService.workApproval(hrManagerWorkApprovalDto, candidateId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// ________________________GET____________________________
	@GetMapping("getWorkApproval/{candidateId}")
	public ResponseEntity<HrManagerWorkApprovalDto> getWorkApproval(@PathVariable long candidateId) {
		HrManagerWorkApprovalDto result = this.hRManagerService.getWorkApproval(candidateId);
		return new ResponseEntity<HrManagerWorkApprovalDto>(result, HttpStatus.OK);
	}

	// ------------ Agreement Approval By Hr Manager-----------------
	// __________________POST____________________
	@PostMapping("agreementApproval/{candidateId}")
	public ResponseEntity<HrManagerAgreementApprovalDto> agreementApproval(
			@RequestBody HrManagerAgreementApprovalDto hrManagerAgreementApprovalDto, @PathVariable long candidateId) {
		HrManagerAgreementApprovalDto result = this.hRManagerService.agreementApproval(hrManagerAgreementApprovalDto,
				candidateId);
		return new ResponseEntity<HrManagerAgreementApprovalDto>(result, HttpStatus.OK);
	}

	// ___________________GET____________________
	@GetMapping("getAgreementApproval/{candidateId}")
	public ResponseEntity<HrManagerAgreementApprovalDto> getAgreementApproval(@PathVariable long candidateId) {
		HrManagerAgreementApprovalDto result = this.hRManagerService.getAgreementApproval(candidateId);
		return new ResponseEntity<HrManagerAgreementApprovalDto>(result, HttpStatus.OK);
	}

	// --------------------BGV Approval By Hr Manager---------------------------
	// _______________________________POST______________________________________________

	@PostMapping("BgvApproval/{candidateId}")
	public ResponseEntity<String> bgvApproval(@RequestBody HrManagerBackgroundVerificationDto bgv,
			@PathVariable long candidateId) {
		String result = this.hRManagerService.bgvApproval(bgv, candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// _________________________________GET________________________________
	@GetMapping("getBgvApproval/{candidateId}")
	public ResponseEntity<HrManagerBackgroundVerificationDto> getBgvApproval(@PathVariable long candidateId) {
		HrManagerBackgroundVerificationDto result = this.hRManagerService.getBgvApproval(candidateId);
		return new ResponseEntity<HrManagerBackgroundVerificationDto>(result, HttpStatus.OK);
	}

	// ------------------For Reject by Hr Manager------------------
	@PostMapping("reject/{candidateId}")
	public ResponseEntity<Integer> rejectHrManager(@PathVariable long candidateId) {
		Integer result = this.hRManagerService.rejectHrManager(candidateId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// --------------------FOR APPOINTMENT LETTER INFO HR
	// MANAGER-----------------------
	@GetMapping("getAppointmentLetterInfo/{candidateId}")
	public ResponseEntity<EmployeeGenerateDto> getAppointmentLetter(@PathVariable long candidateId) {
		EmployeeGenerateDto result = this.hRManagerService.getAppointmentLetter(candidateId);
		return new ResponseEntity<EmployeeGenerateDto>(result, HttpStatus.OK);
	}

	// --------------------Edit And Save Appointment Letter---------------------
	@PostMapping("Edit/AppointmentLetterInfo/{candidateId}")
	public ResponseEntity<String> editAppointment(@RequestBody EmployeeGenerateDto appointmentInfo,
			@PathVariable long candidateId) {
		String result = this.hRManagerService.editAppointment(appointmentInfo, candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------Create Appointment Letter------------------------

	@PostMapping("Create/{candidateId}")
	public ResponseEntity<String> createAppointmentLetter(@RequestBody CreateAppointmentLetterDto appointmentLetterDto,
			@PathVariable long candidateId) {
		String result = this.hRManagerService.createAppointmentLetter(appointmentLetterDto, candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// -------------------------------RELEASE----------------------------------------
	@PostMapping("release/{candidateId}")
	public ResponseEntity<String> releaseAppointmentLetter(@PathVariable long candidateId,
			@RequestBody CreateAppointmentLetterDto appointmentLetterDto) {
		String result = this.hRManagerService.releaseAppointmentLetter(candidateId, appointmentLetterDto);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ------------------------------RELEASE GET-----------------------------------
	@GetMapping("getRelease/{candidateId}")
	public ResponseEntity<EmployeeGenerateDto> getReleaseAppointmentLetter(@PathVariable long candidateId) {
		EmployeeGenerateDto result = this.hRManagerService.getReleaseAppointmentLetter(candidateId);
		return new ResponseEntity<EmployeeGenerateDto>(result, HttpStatus.OK);
	}

	@GetMapping("/Add/{status}")
	public ResponseEntity<Boolean> addCandidatesInHRManager(@PathVariable CandidatesStatus status) {
		boolean result = this.hRManagerService.postCandidatesInHrManager(status);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@PutMapping("/updateManager/{id}")
	public ResponseEntity<String> updateHRManager(@RequestBody HRManager hrManager, @PathVariable Integer id) {

		String result = this.hRManagerService.updateHRManager(hrManager, id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteManager/{id}")
	public ResponseEntity<String> deleteHRManager(@PathVariable Integer id) {

		String result = this.hRManagerService.deleteHRManager(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
