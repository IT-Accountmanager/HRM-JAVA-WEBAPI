package com.hrm.servicesImpls;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Departments.Department;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.ManagerType;
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
			boolean employeeExists = this.employeeRepository.existsByEmployeeId(employeeId);

			if (employeeExists) {
				Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
				WorkHistoryDto workHistory = new WorkHistoryDto();
				workHistory.setDesignation(employee.getDesignation());
				workHistory.setProjectManager(null);
				workHistory.setSubDepartment(employee.getSubDepartment());
				workHistory.setTo(employee.getDateOfReleasing());
				workHistory.setFrom(employee.getDateOfJoining());

				return workHistory;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

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

		employee.setManager(reportingManagerDto.getManager());
		employee.setManagerType(reportingManagerDto.getManagerType());
		employee.setManagerTo(reportingManagerDto.getTo());
		employee.setManagerFrom(reportingManagerDto.getFrom());
		this.employeeRepository.save(employee);

		return "Reporting Manager Added";
	}

	@Override
	public List<ReportingManagerDto> getReportingManager(String employeeId) {

		List<ReportingManagerDto> reportingManagerDto = new ArrayList<ReportingManagerDto>();


		List<Object[]> managerByEmployeeId = this.employeeRepository.findReportingManagerByEmployeeId(employeeId);
		for (Object[] objects : managerByEmployeeId) {
			ReportingManagerDto reportingManager = new ReportingManagerDto();
			reportingManager.setManager((String) objects[0]);

			ManagerType managerType = mapByteToManagerType((Byte) objects[1]);
			reportingManager.setManagerType(managerType);

			Departments department = mapByteToDepartment((Byte) objects[2]);
			reportingManager.setDepartment(department);

			Designation designation = mapByteToDesignation((Byte) objects[3]);
			reportingManager.setDesignation(designation);

			LocalDate from = (objects[4] != null) ? ((Date) objects[4]).toLocalDate() : null;
			reportingManager.setFrom(from);
			LocalDate to = (objects[5] != null) ? ((Date) objects[5]).toLocalDate() : null;
			reportingManager.setTo(to);

			reportingManagerDto.add(reportingManager);

		}

		return reportingManagerDto;
	}

//	@Override
//	public List<ReportingManagerDto> getReportingManager(String employeeId) {
//	    Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
//
//	    List<ReportingManagerDto> result = new ArrayList<>();
//
//	    if (employee != null) {
//	        ReportingManagerDto reportingManagerDto = new ReportingManagerDto();
//	        
//	        // Retrieve manager's details and set them in the DTO
//	        Employee manager = this.employeeRepository.findByEmployeeId(employee.getManager());
//	        if (manager != null) {
//	            reportingManagerDto.setManager(manager.getName()); // Assuming getName() returns the manager's name
//	        } else {
//	            reportingManagerDto.setManager("Unknown Manager");
//	        }
//	        
//	        reportingManagerDto.setManagerType(employee.getManagerType());
//	        reportingManagerDto.setDepartment(employee.getDepartment());
//	        reportingManagerDto.setDesignation(employee.getDesignation());
//	        reportingManagerDto.setFrom(employee.getManagerFrom());
//	        reportingManagerDto.setTo(employee.getManagerTo());
//
//	        result.add(reportingManagerDto);
//	    }
//
//	    return result;
//	}

	@Override
	public List<DirectReportsDto> getDirectReports(String employeeId) {

		List<DirectReportsDto> directReportsDto = new ArrayList<DirectReportsDto>();

		String managerId = employeeId;
		List<Object[]> directReportsData = this.employeeRepository.findByManagerId(managerId);

		for (Object[] data : directReportsData) {
			DirectReportsDto directReport = new DirectReportsDto();
			directReport.setName((String) data[0]);
			Departments.Department department = mapByteToSubDepartment((Byte) data[1]);
			directReport.setSubDepartment(department);

			Designation designation = mapByteToDesignation((Byte) data[2]);
			directReport.setDesignation(designation);
			LocalDate fromDate = (data[3] != null) ? ((Date) data[3]).toLocalDate() : null;
			LocalDate toDate = (data[4] != null) ? ((Date) data[4]).toLocalDate() : null;

			directReport.setFrom(fromDate);
			directReport.setTo(toDate);

			directReportsDto.add(directReport);
		}
		return directReportsDto;
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

	private Departments.Department mapByteToSubDepartment(Byte departmentId) {

		if (departmentId == null) {
			return null;
		}
		int id = departmentId.intValue();
		switch (id) {
		case 0:
			return Departments.Department.DIGITAL_FACTORY_SOLUTION;
		case 1:
			return Departments.Department.INDUSTRIAL_AUTOMATION_SOLUTION;
		case 2:
			return Departments.Department.ENGINEERING_DESIGN_SOLUTION;
		case 3:
			return Departments.Department.BUILDING_INFORMATION_MODELING;
		case 4:
			return Departments.Department.TALENT_ACQUISITION;
		case 5:
			return Departments.Department.HUMAN_RESOURCE;
		case 6:
			return Departments.Department.FINANCE;
		case 7:
			return Departments.Department.SALES;
		case 8:
			return Departments.Department.SYSTEM_ADMIN;
		case 9:
			return Departments.Department.INFORMATION_TECHNOLOGY;
		case 10:
			return Departments.Department.DIGITAL_MARKETING;
		case 11:
			return Departments.Department.DEVELOPMENT;
		default:
			throw new IllegalArgumentException("Invalid sub-department ID: " + id);
		}
	}

	private Departments mapByteToDepartment(Byte departmentId) {

		if (departmentId == null) {
			return null;
		}
		int id = departmentId.intValue();
		switch (id) {
		case 0:
			return Departments.MECHANICAL_ENGINEERING_SERVICES;
		case 1:
			return Departments.OPERATIONAL_DEPARTMENT;
		case 2:
			return Departments.RESEARCH_AND_DEVELOPMENT;
		default:
			throw new IllegalArgumentException("Invalid department ID: " + id);
		}
	}

	private ManagerType mapByteToManagerType(Byte managerType) {

		if (managerType == null) {
			return null;
		}
		int id = managerType.intValue();
		switch (id) {
		case 0:
			return ManagerType.PRIMARY;
		case 1:
			return ManagerType.SECONDARY;
		default:
			throw new IllegalArgumentException("Invalid manager type ID: " + id);
		}
	}

	private Designation mapByteToDesignation(Byte designationId) {
		int id = designationId.intValue();
		switch (id) {
		case 0:
			return Designation.JUNIOR_ENGINEER;
		case 1:
			return Designation.JUNIOR_EXECUTIVE;
		case 2:
			return Designation.ASSOCIATE_ENGINEER;
		case 3:
			return Designation.ASSOCIATE_EXECUTIVE;
		case 4:
			return Designation.SENIOR_ENGINEER;
		case 5:
			return Designation.SENIOR_EXECUTIVE;
		case 6:
			return Designation.TEAM_LEAD;
		case 7:
			return Designation.PROJECT_MANAGER;
		case 8:
			return Designation.PROGRAM_MANAGER;
		case 9:
			return Designation.CXO;
		default:
			throw new IllegalArgumentException("Invalid designation ID: " + id);
		}
	}

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
