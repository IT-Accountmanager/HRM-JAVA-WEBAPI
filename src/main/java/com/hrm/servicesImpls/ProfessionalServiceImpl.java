package com.hrm.servicesImpls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.hrm.models.Employee;
import com.hrm.models.Work;
import com.hrm.payloads.DirectReportsDto;
import com.hrm.payloads.MResignationEditDto;
import com.hrm.payloads.ReportingManagerDto;
import com.hrm.payloads.ResignationInfoDto;
import com.hrm.payloads.UResignationEditDto;
import com.hrm.payloads.WorkHistoryDto;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.IWorkRepository;
import com.hrm.services.IProfessionalService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfessionalServiceImpl implements IProfessionalService {

	@Autowired
	IEmployeeRepository employeeRepository;
	@Autowired
	IWorkRepository workRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public WorkHistoryDto getWorkHistory(String employeeId) {
		try {
			Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {
				WorkHistoryDto workHistory = new WorkHistoryDto();
				workHistory.setDesignation(employee.getDesignation());
				workHistory.setProjectManager(null);
				workHistory.setSubDepartment(employee.getSubDepartment());
				workHistory.setTo(employee.getDateOfReleasing());
				workHistory.setFrom(employee.getDateOfJoining());

				return workHistory;

			} else {
				long candidateId = employee.getCandidateId();
				Work work = this.workRepository.findByCandidateId(candidateId);

				return modelMapper.map(work, WorkHistoryDto.class);
			}
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public String addResignationInfo(MResignationEditDto resignationEditDto, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		employee.setResignationStatus(resignationEditDto.getResignationStatus());
		employee.setLastWorkingDay(resignationEditDto.getLastWorkingDay());
		employee.setNoticePeriod(resignationEditDto.getNoticePeriod());
		// modelMapper.map(resignationEditDto, employee);

		this.employeeRepository.save(employee);

		return " Resignation Info of Employee Id : " + employeeId + " is added Successfully !";
	}

	@Override
	public ResignationInfoDto getResignationInfo(String employeeId) {

		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee != null) {
			ResignationInfoDto resignationInfoDto = new ResignationInfoDto();
			resignationInfoDto.setLastWorkingDay(employee.getLastWorkingDay());
			resignationInfoDto.setNoticePeriod(employee.getNoticePeriod());
			resignationInfoDto.setResignationDate(employee.getResignationDate());
			resignationInfoDto.setResignationStatus(employee.getResignationStatus());

			return resignationInfoDto;
		}
		return null;
	}

	@Override
	public String addReportingManager(ReportingManagerDto reportingManagerDto, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

		employee.setManager(reportingManagerDto.getName());
		employee.setManagerType(reportingManagerDto.getManagerType());
		employee.setManagerTo(reportingManagerDto.getTo());
		employee.setManagerFrom(reportingManagerDto.getFrom());
		this.employeeRepository.save(employee);

		return "Reporting Manager Added";
	}

	@Override
	public List<ReportingManagerDto> getReportingManager(String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

		List<ReportingManagerDto> result = new ArrayList<>();

		if (employee != null) {
			ReportingManagerDto reportingManagerDto = new ReportingManagerDto();
			reportingManagerDto.setName(employee.getManager());
			reportingManagerDto.setManagerType(employee.getManagerType());
			reportingManagerDto.setDepartment(employee.getDepartment());
			reportingManagerDto.setDesignation(employee.getDesignation());
			reportingManagerDto.setFrom(employee.getManagerFrom());
			reportingManagerDto.setTo(employee.getManagerTo());

			result.add(reportingManagerDto);
		}

		return result;
	}

	@Override
	public List<DirectReportsDto> getDirectReports(String employeeId) {
		return Optional.ofNullable(this.employeeRepository.findByEmployeeId(employeeId)).map(employee -> {
			String manager = employee.getManager();
			List<Employee> employeesUnderManager = this.employeeRepository.findAllByManager(manager);

			return employeesUnderManager.stream().map(directReport -> {
				DirectReportsDto directReportsDto = new DirectReportsDto();
				directReportsDto.setName(directReport.getName());
				directReportsDto.setDesignation(directReport.getDesignation());
				directReportsDto.setSubDepartment(directReport.getSubDepartment());
				directReportsDto.setTo(directReport.getManagerTo());
				directReportsDto.setFrom(directReport.getManagerFrom());

				return directReportsDto;
			}).collect(Collectors.toList());
		}).orElse(Collections.emptyList());
	}

	/*
	 * @Override public String addUserResignationInfo(UResignationEditDto
	 * resignationEditDto, String employeeId) { Employee employee =
	 * this.employeeRepository.findByEmployeeId(employeeId); if (employee == null) {
	 * throw new EntityNotFoundException("Employee not found for employeeId: " +
	 * employeeId); }
	 * 
	 * employee.setResignationDate(resignationEditDto.getResignationDate());
	 * 
	 * this.employeeRepository.save(employee);
	 * 
	 * return " Resignation Info of Employee Id : " + employeeId +
	 * " is added Successfully !"; }
	 */

	@Override
	public String addUserResignationInfo(UResignationEditDto resignationDto, String employeeId) {
		if (resignationDto == null || employeeId == null) {
			throw new IllegalArgumentException("Invalid input parameters");
		}

		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(resignationDto, employee);

		this.employeeRepository.save(employee);

		return "Resignation information for Employee Id: " + employeeId + " has been added successfully";
	}

}
