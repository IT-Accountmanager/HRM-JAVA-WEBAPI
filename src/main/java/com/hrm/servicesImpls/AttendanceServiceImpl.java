package com.hrm.servicesImpls;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.aspectj.weaver.ast.Instanceof;
import org.modelmapper.internal.bytebuddy.build.Plugin.Engine.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.exception.ServiceException;
import com.hrm.helper.EnumCollection.AttendanceStatus;
import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Departments.Department;
import com.hrm.helper.Format;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.models.Attendance;
import com.hrm.models.Employee;
import com.hrm.models.Holiday;
import com.hrm.models.LeaveManagementTable;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.AttendanceRequestDto;
import com.hrm.payloads.AttendanceSummaryDto;
import com.hrm.payloads.BasicInfoDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.ManagerAttendanceDetailsDto;
import com.hrm.payloads.ManagerAttendanceEditDto;
import com.hrm.payloads.ManagerAttendanceViewDto;
import com.hrm.payloads.ManagerLeaveDetailsDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.RegularizationManagerEditDto;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.payloads.UserAttendanceSummaryDto;
import com.hrm.repositories.IAttendanceRepository;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.IHolidayRepository;
import com.hrm.repositories.LeaveManagementRepo;
import com.hrm.services.IAttendanceService;
import jakarta.persistence.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	@Autowired
	IHolidayRepository holidayRepository;

	@Autowired
	LeaveManagementRepo leaveManagementRepo;

	private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

	/*
	 * @Override public String clockIn(String employeeId) {
	 * 
	 * Attendance existingAttendance =
	 * this.attendanceRepository.findByEmployeeIdAndDate(employeeId,
	 * LocalDate.now());
	 * 
	 * if (existingAttendance != null) { return
	 * "Clock-in record already exist for Employee Id : " + employeeId; } else {
	 * Attendance attendance = new Attendance();// new api getclock in timee(emp id
	 * , now time and current time)
	 * 
	 * attendance.setEmployeeId(employeeId);
	 * attendance.setMonth(LocalDateTime.now().getMonth());
	 * attendance.setDate(LocalDate.now()); attendance.setInTime(LocalTime.now());
	 * attendance.setAttendanceStatus(AttendanceStatus.Present);
	 * this.attendanceRepository.save(attendance); return
	 * "attendence added of Employee Id : " + employeeId; }
	 * 
	 * }
	 */

	public static HashMap<LocalDate, Integer> getAllDates(LocalDate startDate, LocalDate endDate) {
		HashMap<LocalDate, Integer> datesMap = new HashMap<>();
		LocalDate currentDate = startDate;
		while (!currentDate.isAfter(endDate)) {
			datesMap.put(currentDate, 0); // Dummy value, you can put any value here
			currentDate = currentDate.plusDays(1);
		}
		return datesMap;
	}

	@Override
	public String clockIn(String employeeId) {
		try {

			LocalDate currentDate = LocalDate.now();

			Attendance existingAttendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, currentDate);
			if (existingAttendance != null) {
				logger.info("Clock-in record already exists for Employee Id: {}", employeeId);
				return "Clock-in record already exists for Employee Id : " + employeeId;
			} else {
				logger.info("Clock-in record not present for the employee Id: {}", employeeId);

				Attendance attendance = new Attendance();
				attendance.setEmployeeId(employeeId);
				attendance.setMonth(LocalDateTime.now().getMonth());
				attendance.setDate(currentDate);
				attendance.setInTime(LocalTime.now());

				this.attendanceRepository.save(attendance);

				logger.info("Attendance added for Employee Id: {}", employeeId);
				return "Attendance added for Employee Id : " + employeeId;
			}
		} catch (Exception e) {
			logger.error("Error occurred while clocking in for Employee Id: {}", employeeId, e);
			return "Error occurred while clocking in for Employee Id : " + employeeId;
		}
	}

	@Override
	public String clockOut(String employeeId) {

		try {
			LocalDate date = LocalDate.now();
			Attendance attendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
			LocalTime outTime = LocalTime.now();

			// Format the out time to HH:MM format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedOutTime = outTime.format(formatter);

			attendance.setOutTime(outTime);
//		Duration workDuration = Duration.between(attendance.getInTime(), outTime);
//
//		long nanoseconds = workDuration.toNanos();
//		double hours = nanoseconds / (double) Duration.ofHours(1).toNanos();

			Duration workDuration = Duration.between(attendance.getInTime(), outTime);
			long workHours = workDuration.toMinutes();
			attendance.setWorkHrs(workHours);

			this.attendanceRepository.save(attendance);
			logger.info("Successfully checked out Employee ID {} at {}", employeeId, formattedOutTime);
			return "Check Out of Employee Id " + employeeId + " at " + formattedOutTime;
		} catch (Exception e) {
			logger.error("Error occurred during clock out for employee ID {}", employeeId, e);
			// Return a message indicating failure
			return "Error occurred during clock out. Please check logs for details.";
		}

	}

	@Override
	public Set<UserAttendanceDto> allAttendance(String employeeId) {

		logger.debug("In All Attendance Method");

		Set<UserAttendanceDto> fullMonthAttendance = new TreeSet<>((a, b) -> {
			LocalDate dateA = LocalDate.parse(a.getDate());
			LocalDate dateB = LocalDate.parse(b.getDate());
			return dateA.compareTo(dateB);

		});
		// all dates data
		try {
			List<Attendance> allAttendanceList = attendanceRepository.findAllByEmployeeId(employeeId);
			logger.debug("allAttendanceList :", allAttendanceList);

			if (allAttendanceList.isEmpty()) {
				return Collections.emptySet();
			}

			// Created for Holiday

			List<Object[]> holidayObjects = this.holidayRepository.getAllHolidays();

			Map<String, String> holidayMap = holidayObjects.stream()
					.collect(Collectors.toMap(arr -> (String) arr[0], arr -> (String) arr[1]));

//			Map<String, String> holidayMap = this.holidayRepository.getAllHolidays().stream()
//					.collect(Collectors.toMap(Holiday::getDate, Holiday::getHolidayName));

			HashMap<String, String> holidayList = new HashMap<>(holidayMap);

			LocalDate startDate = LocalDate.now().withDayOfMonth(1); // Start of the current month
			LocalDate endDate = startDate.plusMonths(1).minusDays(1); // End of the current month

			HashMap<LocalDate, Integer> datesMap = getAllDates(startDate, endDate);

			System.out.println("All dates within the current month:" + datesMap);
			for (LocalDate currentDate : datesMap.keySet()) {
				System.out.println(currentDate);
//				boolean currentDatePresent = allAttendanceList.stream().map(Attendance::getDate)
//						.anyMatch(date -> date.equals(currentDate));
				Optional<Attendance> currentDateMatched = allAttendanceList.stream()
						.filter(clasz -> clasz.getDate().equals(currentDate)).findFirst();

				if (currentDateMatched.isPresent()) {
					// fullMonthAttendance.add(currentDateMatched.get());

					Attendance matchedAttendance = currentDateMatched.get();
					UserAttendanceDto attendanceDto = new UserAttendanceDto();
					attendanceDto.setDate(matchedAttendance.getDate().toString());
					attendanceDto.setEmployeeId(matchedAttendance.getEmployeeId());
					attendanceDto.setMonth(matchedAttendance.getMonth().toString());
					attendanceDto.setInTime(matchedAttendance.getInTime());
					fullMonthAttendance.add(attendanceDto);
				} else {

					UserAttendanceDto attendance = new UserAttendanceDto();
					attendance.setDate(currentDate.toString());
					attendance.setMonth(currentDate.getMonth().toString());
					attendance.setEmployeeId(employeeId);

					DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
					if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
						attendance.setAttendanceStatus('w');
						System.out.println("Current date falls on a weekend (Saturday or Sunday)");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String formattedDate = currentDate.format(formatter);

						String holiday = holidayList.getOrDefault(formattedDate, "");

						if (!holiday.isEmpty()) {
							attendance.setAttendanceStatus('h');
							logger.debug(" Current date falls on a Holiday  :{} ", holiday);
						}
					}
					System.out.println("Current date falls on a weekday");

					fullMonthAttendance.add(attendance);
				}

			}

//			for (Attendance attendance : allByEmployeeId) {
//
//				UserAttendanceDto attendanceDto = new UserAttendanceDto();
//
//				attendanceDto.setEmployeeId(employeeId);
//				attendanceDto.setMonth(attendance.getMonth());
//				attendanceDto.setDate(Format.getFormattedDate(attendance.getDate()));
//				attendanceDto.setInTime(attendance.getInTime());
//				attendanceDto.setOutTime(attendance.getOutTime());
//				attendanceDto.setRemarks(attendance.getRemarks());
//
//				if (attendance.getInTime() != null && attendance.getOutTime() != null) {
//					attendanceDto.setWorkHrs((Duration.between(attendance.getInTime(), attendance.getOutTime())));
//				} else {
//					attendanceDto.setWorkHrs((Duration.ZERO));
//				}
//
//				attendanceDto.setAttendanceStatus(attendance.getAttendanceStatus());
//				attendanceDto.setProjectId(attendance.getProjectId());
//				Integer appliedHrsForBilling = attendance.getAppliedHrsForBilling();
//				if (appliedHrsForBilling != null) {
//					attendanceDto.setAppliedHrsForBilling(appliedHrsForBilling);
//				}
//				attendanceDto.setApprovedHrsForBilling(attendance.getApprovedHrsForBilling());
//
//				attendanceData.add(attendanceDto);
//			}
//			// __________________________________________________________
//
//			int currentYear = LocalDate.now().getYear();
//			Month month = LocalDate.now().getMonth();
//			LocalDate firstDayOfMonth = LocalDate.of(currentYear, month, 1);
//			LocalDate lastDayOfMonth = LocalDate.of(currentYear, month, month.length(LocalDate.now().isLeapYear()));
//
//			for (LocalDate date = firstDayOfMonth; !date.isAfter(lastDayOfMonth); date = date.plusDays(1)) {
//				UserAttendanceDto attendanceDto = new UserAttendanceDto();
//				attendanceDto.setDate(date.toString());
//				if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
//					attendanceDto.setEmployeeId(employeeId);
//					attendanceDto.setAttendanceStatus('W');
//				}
//				attendanceDateSet.add(attendanceDto);
//			}
//
//			for (UserAttendanceDto dateData : attendanceDateSet) {
//
//				String date = dateData.getDate();
//				boolean isDatePresent = attendanceData.stream()
//						.anyMatch(attendanceDto -> attendanceDto.getDate().equals(date));
//
//				if (isDatePresent) {
//
//					UserAttendanceDto foundRecord = attendanceData.stream().filter(x -> x.getDate().equals(date))
//							.findFirst().orElse(null);
//					if (foundRecord != null) {
//						attendanceAllData.add(foundRecord);
//					}
//
//				} else {
//
////					 dateData = new UserAttendanceDto();
////						dateData.setEmployeeId(employeeId);
//
////					 
//					attendanceAllData.add(dateData);
//				}
//			}

			// __________________________________________________________

			return fullMonthAttendance;

		} catch (Exception e) {

			logger.error("Error retrieving attendance for employeeId: {}", employeeId, e);
			throw new ServiceException("Error retrieving attendance for employeeId: " + employeeId, e);
		}
	}

	@Override
	public Set<UserAttendanceDto> allAttendance(String employeeId, Integer month, Integer year) {
		logger.debug("In Side All Attendance Method");
		Object[] manager = this.attendanceRepository.findManager(employeeId);

		LocalDate startDate = LocalDate.now().withDayOfMonth(1); // Start of the current month
		LocalDate endDate = startDate.plusMonths(1).minusDays(1); // End of the current month

		List<Object[]> holidayObjects = this.holidayRepository.getAllHolidays();
		Set<UserAttendanceDto> fullMonthAttendance = new TreeSet<>((a, b) -> {
			LocalDate dateA = LocalDate.parse(a.getDate());
			LocalDate dateB = LocalDate.parse(b.getDate());
			return dateA.compareTo(dateB);

		});
		try {
			logger.debug("In Side Try Block and Employee Id : {} ", employeeId);

			List<Object[]> allAttendanceList;
			List<Object[]> allLeaves;

			if (month != 0 && year != 0) {
				startDate = LocalDate.of(year, month, 1); // Start of the current month
				endDate = startDate.plusMonths(1).minusDays(1); // End of the current month
				System.out.println("*************1.Year = " + year + "\n" + "Month = " + month + "***************");
				logger.info("1.Employee Id. : {}", employeeId);

				allLeaves = leaveManagementRepo.findLeaves(employeeId, month, year);
				if (allLeaves.isEmpty()) {
					logger.info("No Leaves found for employee Id : {} for {} month {} year", employeeId, month, year);
					// return Collections.emptySet();
				}
				allAttendanceList = attendanceRepository.findAllAttendance(employeeId, month, year);
				if (allAttendanceList.isEmpty()) {
					logger.info("No records found for employee Id : {} for {} month {} year", employeeId, month, year);
					// return Collections.emptySet();
				}
				logger.info("Records found for employee Id : {} for {} month {} year", employeeId, month, year);
			} else if (month != 0) {
				year = LocalDate.now().getYear();
				startDate = LocalDate.of(year, month, 1);
				endDate = startDate.plusMonths(1).minusDays(1);
				System.out.println("*************2.Year = " + year + "\n" + "Month = " + month + "***************");
				logger.info("2.Employee Id. : {}", employeeId);

				allLeaves = leaveManagementRepo.findLeaves(employeeId, month, year);
				if (allLeaves.isEmpty()) {
					logger.info("No Leaves found for employee Id : {} for {} month {} year", employeeId, month, year);
					// return Collections.emptySet();
				}
				allAttendanceList = attendanceRepository.findAllAttendance(employeeId, month, year);
				if (allAttendanceList.isEmpty()) {
					logger.info("No records found for employee Id : {} for {} month {} year", employeeId, month, year);
					// return Collections.emptySet();
				}
				logger.info("Records found for employee Id : {} for {} month {} year", employeeId, month, year);
			} else {
				year = LocalDate.now().getYear();
				month = LocalDate.now().getMonthValue();
				startDate = LocalDate.of(year, month, 1);
				endDate = startDate.plusMonths(1).minusDays(1);

				System.out.println("*************3.Year = " + year.getClass().getSimpleName() + "\n" + "Month = "
						+ month.getClass().getSimpleName() + "***************");
				logger.info("3.Employee Id. : {}", employeeId);
				allLeaves = leaveManagementRepo.findLeaves(employeeId, month, year);
				if (allLeaves.isEmpty()) {
					logger.info("No Leaves found for employee Id : {} for {} month {} year", employeeId, month, year);
					// return Collections.emptySet();
				}
				allAttendanceList = attendanceRepository.findAllAttendance(employeeId, month, year);
				if (allAttendanceList.isEmpty()) {
					logger.info("No records found for employee Id : {} for {} month {} year", employeeId, month, year);
					// return Collections.emptySet();
				}
				logger.info("Records found for employee Id : {} for {} month {} year", employeeId, month, year);
			}

			Map<String, String> holidayMap = holidayObjects.stream()
					.collect(Collectors.toMap(arr -> (String) arr[0], arr -> (String) arr[1]));
			HashMap<String, String> holidayList = new HashMap<>(holidayMap);

			Map<String, String> leaveMap = allLeaves.stream()
					.collect(Collectors.toMap(arr -> ((Date) arr[1]).toString(), arr -> ((Date) arr[2]).toString()));
			HashMap<String, String> leaveDatesList = new HashMap<>(leaveMap);

			HashMap<LocalDate, Integer> datesMap = getAllDates(startDate, endDate);

			logger.debug("Start Date: {}", startDate);
			logger.debug("End Date: {}", endDate);
			for (LocalDate currentDate : datesMap.keySet()) {
				logger.debug("Processing attendance for date: {}", currentDate);

				System.out.println(currentDate);

				Optional<Object[]> currentDateMatched = allAttendanceList.stream().filter(clasz -> {
					Object[] clazz = (Object[]) clasz;
					logger.debug("Data type of Current Date: {}", currentDate.getClass().getSimpleName());
					logger.debug("Data type of clazz[2]: {}", clazz[2].getClass().getSimpleName());
					logger.debug("Current Date: {}, clazz[2]: {}", currentDate, clazz[2]);

					LocalDate attendanceDate = ((Date) clazz[2]).toLocalDate();
					return attendanceDate.equals(currentDate);
				}).findFirst();

				if (currentDateMatched.isPresent()) {
					logger.debug("Attendance found for date: {}", currentDate);

					Object[] matchedAttendance = currentDateMatched.get();

					UserAttendanceDto attendanceDto = new UserAttendanceDto();
					// select a.employee_id, a.month , a.date , a.in_time , a.out_time , a.work_hrs
					// , a.attendance_status , m.name AS manager , a.project_id ,
					// a.applied_hrs_for_billing , a.approved_hrs_for_billing , a.remarks

					try {
						attendanceDto.setEmployeeId(matchedAttendance[0].toString());
					} catch (Exception e) {
						logger.error("Error setting employeeId: {}", e.getMessage());
					}
					try {
						attendanceDto.setMonth(Month.of(Integer.parseInt(matchedAttendance[1].toString()) + 1).name());
					} catch (Exception e) {
						logger.error("Error setting month: {}", e.getMessage());
					}
					try {
						attendanceDto.setDate((((Date) matchedAttendance[2]).toLocalDate()).toString());
					} catch (Exception e) {
						logger.error("Error setting date: {}", e.getMessage());
					}
					try {
						attendanceDto.setInTime(((Time) matchedAttendance[3]).toLocalTime());
					} catch (Exception e) {
						logger.error("Error setting inTime: {}", e.getMessage());
					}

					if (matchedAttendance[4] instanceof Time && matchedAttendance[4] != null) {
						Time outTime = (Time) matchedAttendance[4];
						attendanceDto.setOutTime(outTime.toLocalTime());
					}

					try {
//						BigDecimal workHrsBigDecimal = (BigDecimal) matchedAttendance[5];
//						long seconds = workHrsBigDecimal.longValue();
//						Duration workHrs = Duration.ofSeconds(seconds);
						attendanceDto.setWorkHrs((Long) matchedAttendance[5]);

						// attendanceDto.setWorkHrs((Duration) matchedAttendance[5]);
					} catch (Exception e) {
						logger.error("Error setting workHrs: {}", e.getMessage());
					}
					try {
						attendanceDto.setAttendanceStatus((char) matchedAttendance[6]);
					} catch (Exception e) {
						logger.error("Error setting Attendance Status: {}", e.getMessage());
					}
					try {
						if (matchedAttendance[7].toString() != null) {
							attendanceDto.setManager(matchedAttendance[7].toString());
							// manager = matchedAttendance[7].toString();
						}

					} catch (Exception e) {
						logger.error("Error setting manager: matchedAttendance[7] is null");
					}
					try {
						if (matchedAttendance[8] != null) {
							attendanceDto.setProjectId(matchedAttendance[8].toString());
						}
					} catch (Exception e) {
						logger.error("Error setting projectId: {}", e.getMessage());
					}

					try {
						if (matchedAttendance[9] != null) {
							attendanceDto.setAppliedHrsForBilling((int) matchedAttendance[9]);
						}
					} catch (Exception e) {
						logger.error("Error setting appliedHrsForBilling: {}", e.getMessage());
					}
					try {
						if (matchedAttendance[10] != null) {
							attendanceDto.setApprovedHrsForBilling((int) matchedAttendance[10]);
						}
					} catch (Exception e) {
						logger.error("Error setting approvedHrsForBilling: {}", e.getMessage());
					}
					try {
						if (matchedAttendance[11] != null) {
							attendanceDto.setRemarks(matchedAttendance[11].toString());
						}
					} catch (Exception e) {
						logger.error("Error setting remarks: {}", e.getMessage());
					}
					try {
						if (matchedAttendance[12] != null) {
							attendanceDto.setRequestedInTime(((Time) matchedAttendance[12]).toLocalTime());
						}
					} catch (Exception e) {
						logger.error("Error setting requested in time: {}", e.getMessage());
					}
					try {
						if (matchedAttendance[13] != null) {
							attendanceDto.setRequestedOutTime(((Time) matchedAttendance[13]).toLocalTime());
						}
					} catch (Exception e) {
						logger.error("Error setting requested out time: {}", e.getMessage());
					}
					try {
						if (matchedAttendance[14] != null) {
							attendanceDto.setRequestedWorkHrs((Long) matchedAttendance[14]);
						}
					} catch (Exception e) {
						logger.error("Error setting requested work hours: {}", e.getMessage());
					}

					fullMonthAttendance.add(attendanceDto);
				} else {
					logger.debug("Attendance not found for date: {}", currentDate);

					UserAttendanceDto attendance = new UserAttendanceDto();
					attendance.setDate(currentDate.toString());
					attendance.setMonth(currentDate.getMonth().toString());
					attendance.setEmployeeId(employeeId);
					if (manager != null && manager.length > 0) {
						attendance.setManager(manager[0].toString());
					}
					DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
					if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
						attendance.setAttendanceStatus('w');
						System.out.println("Current date falls on a weekend (Saturday or Sunday)");
					} else {
						logger.debug(" Inside Else block to set Attendance Status   ");

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String formattedDate = currentDate.format(formatter);

						String holiday = holidayList.getOrDefault(formattedDate, "");

						String leave = leaveDatesList.getOrDefault(formattedDate, "");
						// here i want to check currentDate is present in leaveDatesList or not

						if (!holiday.isEmpty()) {
							logger.debug(" Current date falls on a Holiday  :{} ", holiday);
							attendance.setAttendanceStatus('h');
						} else if (!leave.isEmpty()) {
							logger.debug(" Current date falls on a Leave  :{} ", holiday);
							attendance.setAttendanceStatus('l');
						}

					}
					System.out.println("Current date falls on a weekday");

					fullMonthAttendance.add(attendance);
				}

			}
			return fullMonthAttendance;

		} catch (Exception e) {

			logger.error("Error retrieving attendance for employeeId: {}", employeeId, e);
			throw new ServiceException("Error retrieving attendance for employeeId: " + employeeId, e);
		}
	}

	private String getMonthName(Month month) {
		return (month != null) ? month.name() : "Month Not Set";
	}

	@Override
	public List<AttendanceSummaryDto> getSummary(Integer month, Integer year) {
		logger.info("Inside Summary Service Implementation");
		List<AttendanceSummaryDto> attendanceSummaryList = new ArrayList<AttendanceSummaryDto>();
		try {
			final Integer[] _month = { month };
			final Integer[] _year = { year };

			if (month == null && year == null) {
				_month[0] = LocalDate.now().getMonthValue();
				_year[0] = LocalDate.now().getYear();
			} else if (month == null) {
				_month[0] = LocalDate.now().getMonthValue();
			} else if (year == null) {
				_year[0] = LocalDate.now().getYear();
			}

			List<Object[]> summary = this.attendanceRepository.getSummary(_month[0], _year[0]);

			attendanceSummaryList = summary.stream().map(summaryObject -> {
				Integer monthValue = _month[0];
				Integer yearValue = _year[0];
				AttendanceSummaryDto summaryDto = new AttendanceSummaryDto();
				summaryDto.setEmployeeId(summaryObject[0].toString());
				summaryDto.setEmployeeName(summaryObject[1].toString());
				summaryDto.setMonth(monthValue);
				summaryDto.setManager(summaryObject[2].toString());
				summaryDto.setWorkingDays(calculateWorkingDays(monthValue, yearValue));
				summaryDto.setPresentDays(((Long) summaryObject[3]).intValue());
				summaryDto.setLeaves((Double) summaryObject[4]);
				summaryDto.setTotalDays((Integer) summaryObject[5]);
				summaryDto.setLop(calculateLop(summaryDto.getWorkingDays(), summaryDto.getPresentDays()));
				Object obj = summaryObject[6];
				if (obj instanceof Integer) {
					summaryDto.setApprovedBillableHours(((Integer) obj).longValue());
				} else if (obj instanceof Long) {
					summaryDto.setApprovedBillableHours((Long) obj);
				}
				return summaryDto;
			}).collect(Collectors.toList());

		} catch (Exception e) {
			logger.error("Error retrieving summary attendance  : {}", e);
			throw new ServiceException("Error retrieving summary attendance : " + e);

		}

		return attendanceSummaryList;
	}

	@Override
	public List<UserAttendanceSummaryDto> getSummary(String employeeId, Integer year) {
		logger.info("Inside of User attendance Summary Implimentation");

		List<UserAttendanceSummaryDto> result = new ArrayList<UserAttendanceSummaryDto>();

		try {

			final Integer[] _year = { year };

			if (year == null) {
				_year[0] = LocalDate.now().getYear();
			}

			// List<Object[]> existingAttendance =
			// this.attendanceRepository.getSummary(employeeId, year);

			for (int month = 1; month <= 12; month++) {
				Integer yearValue = _year[0];
				UserAttendanceSummaryDto summaryDto = new UserAttendanceSummaryDto();
				summaryDto.setMonth(getMonthName(month));
				summaryDto.setWorkingDays(calculateWorkingDays(month, yearValue));
				summaryDto.setPresentDays(calculatePresentDays(month, yearValue));
				summaryDto.setLeaves(calculateLeaves(month, yearValue));
				summaryDto.setTotalDays(calculateTotalDays(month, yearValue));
				summaryDto.setLop(null);
				summaryDto.setApprovedBillableHours(calculateTotalApprovedBillableHours(month, yearValue));
				result.add(summaryDto);
			}

		} catch (Exception e) {
			logger.error("Error retriving Attendance Summary of employee Id : {} for year : {} . Reason : {}",
					employeeId, year, e);
			throw new ServiceException("Error retriving Attendance Summary of employee Id : " + employeeId
					+ "for year : " + year + " . Reason : " + e);
		}
		return result;
	}

	private Long calculateTotalApprovedBillableHours(int month, Integer yearValue) {
		logger.debug("Calculating Approved Billable Hours for month {} and year {}", month, yearValue);
		Long billingHours = this.attendanceRepository.calculateTotalApprovedBillableHours(month, yearValue);
		logger.debug("Approved Billable Hours calculated : {}", billingHours);
		return billingHours;
	}

	public Integer calculateTotalDays(int month, Integer yearValue) {
		if (yearValue == null || yearValue < 0) {
			throw new IllegalArgumentException("Invalid year value");
		}

		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month value");
		}

		YearMonth yearMonth = YearMonth.of(yearValue, month);

		int totalDays = yearMonth.lengthOfMonth();

		return totalDays;
	}

	private Double calculateLeaves(int month, Integer yearValue) {
		logger.debug("Calculating leavess for month {} and year {}", month, yearValue);
		Double leaves = this.attendanceRepository.calculateLeaves(month, yearValue);
		logger.debug("Leaves calculated : {}", leaves);
		return leaves;
	}

	private Integer calculatePresentDays(int month, Integer yearValue) {
		logger.debug("Calculating present days for month {} and year {}", month, yearValue);
		Integer pres = this.attendanceRepository.calculatePresentDays(month, yearValue);
		logger.debug("Present days calculated : {}", pres);
		return pres;
	}

	private String getMonthName(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	private Integer calculateLop(Integer workingDays, Integer presentDays) {
		return workingDays - presentDays;
	}

	private Integer calculateWorkingDays(Integer monthValue, Integer _year) {

		LocalDate startDay = LocalDate.of(_year, monthValue, 1);
		int daysInMonth = startDay.getMonth().length(startDay.isLeapYear());

		int workingDays = 0;
		for (int i = 1; i <= daysInMonth; i++) {
			LocalDate currentDate = LocalDate.of(_year, monthValue, i);
			DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
			if (dayOfWeek != dayOfWeek.SATURDAY && dayOfWeek != dayOfWeek.SUNDAY) {
				workingDays++;
			}
		}
		return workingDays;
	}

	@Override
	public AttendanceEmployeeDto getAttendance(String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
		AttendanceEmployeeDto dto = new AttendanceEmployeeDto();

		try {

			logger.info("Attempting to retrieve attendance for employee with ID: {}", employeeId);

			dto.setEmployeeId(employeeId);
			dto.setMonth(attendance.getMonth());
			dto.setDate(attendance.getDate());
			dto.setInTime(attendance.getInTime());
			dto.setOutTime(attendance.getOutTime());
			dto.setWorkHours(dto.calculateWorkHours());
			dto.setWorkHrs(dto.formatWorkHours());

			dto.setAttendanceStatus((Character) null);
			// dto.setAttendanceStatus(null);

			dto.setManager(null);
			dto.setProjectId(null);
			dto.setAppliedHoursForBilling(null);
			dto.setApprovedHoursForBilling(null);
			dto.setBillingHoursStatus(null);

			logger.info("Attendance retrieved successfully for employee with ID: {}", employeeId);

			return dto;
		} catch (Exception e) {
			logger.error("An error occurred while processing attendance for employee with ID: {}", employeeId, e);

			// Optionally, you can rethrow the exception or return a default/empty DTO
			throw new RuntimeException("Error retrieving attendance information for employee with ID: " + employeeId,
					e);
		}
	}

	@Override
	public String deleteAttendance(Integer id) {
		try {
			attendanceRepository.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

	@Override
	public String editAttendance(Attendance attendance, Integer id) {

		try {
			if (this.attendanceRepository.existsById(id)) {
				// attendance.setId(id);
				this.attendanceRepository.save(attendance);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

//	@Override
//<<<<<<< HEAD
//	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
//		List<Attendance> attendanceListOfEmployee = this.attendanceRepository.findAllByEmployeeId(employeeId);
//		List<LocalDate> listOfDate = billableHoursDto.getListOfDate();
//
//		for (LocalDate date : listOfDate) {
//
//		}
//
//		return null;
//	}

//	@Override
//	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
//		return null;
//	}

	@Override
	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
		LocalDate date = billableHoursDto.getDate();
		int productionHours = billableHoursDto.getProductionHours();
		int otherHours = billableHoursDto.getOtherHours();
		int appliedHrsForBilling = billableHoursDto.getAppliedHrsForBilling();

		Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (attendance != null) {

			attendance.setDate(date);

			attendance.setProductionHours(productionHours);
			attendance.setOtherHours(otherHours);
			attendance.setAppliedHrsForBilling(appliedHrsForBilling);

			attendanceRepository.save(attendance);
			return "Billable hours added for employee " + employeeId + " on " + date;
		} else {
			return "Attendance not existed for employee " + employeeId + " on " + date;
		}
	}

	/*
	 * @Override public String addRegularizationHours(RegularizationHoursDto
	 * regularizationHoursDto, String employeeId) { LocalDate date =
	 * regularizationHoursDto.getDate(); LocalTime inTime =
	 * regularizationHoursDto.getInTime(); LocalTime outTime =
	 * regularizationHoursDto.getOutTime(); String regularisationReason =
	 * regularizationHoursDto.getRegularisationReason();
	 * 
	 * Duration difference = Duration.between(inTime, outTime);
	 * 
	 * // Define the threshold duration of 9 hours and 30 minutes Duration threshold
	 * = Duration.ofHours(9).plusMinutes(30); Attendance attendance =
	 * attendanceRepository.findByEmployeeId(employeeId);
	 * 
	 * if (attendance != null) { attendance.setDate(date);
	 * attendance.setInTime(inTime); attendance.setOutTime(outTime);
	 * attendance.setRegularisationReason(regularisationReason);
	 * 
	 * long nanoseconds = difference.toNanos(); double hours = nanoseconds /
	 * (double) Duration.ofHours(1).toNanos();
	 * 
	 * if (difference.compareTo(threshold) < 0) {
	 * 
	 * Duration remainingHours = threshold.minus(difference);
	 * attendance.setRegularisationRequestHours(remainingHours); } else {
	 * attendance.setRegularisationRequestHours(Duration.ZERO); }
	 * 
	 * attendanceRepository.save(attendance); return
	 * "Regularization hours added for employee " + employeeId; } else { return
	 * "Attendance record not found for employee " + employeeId; } }
	 */

//	@Override
//	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
//	    LocalDate date = regularizationHoursDto.getDate();
//	    LocalTime inTime = regularizationHoursDto.getInTime();
//	    LocalTime outTime = regularizationHoursDto.getOutTime();
//	    String regularisationReason = regularizationHoursDto.getRegularisationReason();
//	    
//	    // Calculate the duration between exactInTime and exactOutTime
//	    Duration difference = Duration.between(inTime, outTime);
//	    
//	    // Define the threshold duration of 9 hours and 30 minutes
//	    Duration threshold = Duration.ofHours(9).plusMinutes(30);
//
//	    Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
//
//	    if (attendance != null) {
//	        attendance.setDate(date);
//	        attendance.setInTime(inTime);
//	        attendance.setOutTime(outTime);
//	        attendance.setRegularisationReason(regularisationReason);
//	        
//	        // Check if the attendance status is 'A' (Anomaly)
//	        if (attendance.getAttendanceStatus() == 'A') {
//	            // If Anomaly, add regularization hours to worked hours
//	            attendance.setWorkHrs(attendance.getWorkHrs().plus(difference));
//	        } else {
//	            // If not Anomaly, proceed with regular regularization handling
//	            // Check if the duration is less than the threshold
//	            if (difference.compareTo(threshold) < 0) {
//	                // If less, set RegularisationRequestHours to the difference from the threshold
//	                Duration remainingTime = threshold.minus(difference);
//	                
//	                // Convert remainingTime to hours and minutes
//	                long remainingHours = remainingTime.toHours();
//	                long remainingMinutes = remainingTime.minusHours(remainingHours).toMinutes();
//
//	                // Set RegularisationRequestHours as a formatted string
//	                attendance.setRegularisationRequestHours(String.format("%02d:%02d", remainingHours, remainingMinutes));
//	            } else {
//	                // If greater or equal, set RegularisationRequestHours to zero
//	                attendance.setRegularisationRequestHours("00:00");
//	            }
//	        }
//
//	        attendanceRepository.save(attendance);
//	        return "Regularization hours added for employee " + employeeId;
//	    } else {
//	        return "Attendance record not found for employee " + employeeId;
//	    }
//	}

	@Override
	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
		LocalDate date = regularizationHoursDto.getDate();
		LocalTime inTime = regularizationHoursDto.getRequestedInTime();
		LocalTime outTime = regularizationHoursDto.getRequestedOutTime();
		String regularisationReason = regularizationHoursDto.getRegularisationReason();
		long regularisationRequestHours = regularizationHoursDto.getRequestedWorkHrs();

//	    Duration regularisationRequestHours = Duration.ofHours(regularizationHoursDto.getHours())
//                .plusMinutes(regularizationHoursDto.getMinutes());

		// Retrieve the attendance record for the employee on the given date
		Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (attendance != null) {
			attendance.setDate(date);
			attendance.setRequestedInTime(inTime);
			attendance.setRequestedOutTime(outTime);
			attendance.setRegularisationReason(regularisationReason);
			attendance.setRequestedWorkHrs(regularisationRequestHours);

			// Check if the attendance status is 'A' (Anomaly)
			if (attendance.getAttendanceStatus() == 'A') {
				// If attendance status is 'A', change it to 'R' (Regularization)
				attendance.setAttendanceStatus('R');
			}

			// Save the updated attendance record
			attendanceRepository.save(attendance);

			return "Regularization hours added for employee " + employeeId;
		} else {
			return "Attendance record not found for employee " + employeeId;
		}
	}

//	@Override
//	public String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId) {
//
//	    LeaveType leaveType = applyLeaveDto.getLeaveType();
//	    LocalDate startDate = applyLeaveDto.getStartDate();
//	    LocalDate endDate = applyLeaveDto.getEndDate();
//	    Half half1 = applyLeaveDto.getHalf1();
//	    Half half2 = applyLeaveDto.getHalf2();
//	    String leaveReason = applyLeaveDto.getLeaveReason();
//
//	    Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
//	    if (attendance == null) {
//	        attendance = new Attendance();
//	        attendance.setEmployeeId(employeeId);
//	    }
//
//	    attendance.setLeaveType(leaveType);
//	    attendance.setStartDate(startDate);
//	    attendance.setEndDate(endDate);
//	    attendance.setHalf1(half1);
//	    attendance.setHalf2(half2);
//	    attendance.setLeaveReason(leaveReason);
//
//	
//
//	    // Fetch the month from start date
//	    Month startMonth = startDate.getMonth();
//	    // Fetch the month from end date
//	    Month endMonth = endDate.getMonth();
//	    
//	    // Calculate the number of days between start date and end date
//	    long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
//
//	    attendanceRepository.save(attendance);
//
//	    return "Leave Added Successfully for employee Id " + employeeId + ". Number of days: " + numberOfDays +
//	            ". Start Month: " + startMonth + ". End Month: " + endMonth;
//	}

//	@Override
//	public ApplyLeaveDto getLeave(String employeeId) {
//		// Assuming LeaveRepository has a method to find leave by employeeId
//		Attendance leave = attendanceRepository.findByEmployeeId(employeeId);
//		// Map Leave entity to ApplyLeaveDto
//		if (leave != null) {
//			ApplyLeaveDto applyLeaveDto = new ApplyLeaveDto();
//			applyLeaveDto.setHalf1(leave.getHalf1());
//			applyLeaveDto.setHalf2(leave.getHalf2());
//			applyLeaveDto.setLeaveReason(leave.getLeaveReason());
//			applyLeaveDto.setLeaveType(leave.getLeaveType());
//			applyLeaveDto.setStartDate(leave.getStartDate());
//			applyLeaveDto.setEndDate(leave.getEndDate());
//			// Set other fields as needed
//			return applyLeaveDto;
//		} else {
//			throw new RuntimeException("Leave Record is not found for employee " + employeeId);
//		}
//	}

	@Override
	public BillableHoursDto getBillableHours(String employeeId, LocalDate date) {
		Attendance billableHours = attendanceRepository.findByEmployeeId(employeeId);

		if (billableHours != null) {
			BillableHoursDto billableHoursDto = new BillableHoursDto();

			billableHoursDto.setProductionHours(billableHours.getProductionHours());
			billableHoursDto.setOtherHours(billableHours.getOtherHours());
			billableHoursDto.setAppliedHrsForBilling(billableHours.getAppliedHrsForBilling());

			return billableHoursDto;
		}

		else {
			return null;
		}
	}

	@Override
	public RegularizationHoursDto getRegularizationHours(String employeeId, LocalDate date) {
		Attendance regularizationHours = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (regularizationHours != null) {
			RegularizationHoursDto regularizationHoursDto = new RegularizationHoursDto();

			regularizationHoursDto.setRequestedInTime(regularizationHours.getRequestedInTime());
			regularizationHoursDto.setRequestedOutTime(regularizationHours.getOutTime());
			regularizationHoursDto.setDate(regularizationHours.getDate());
			regularizationHoursDto.setRegularisationReason(regularizationHours.getRegularisationReason());
			regularizationHoursDto.setRequestedWorkHrs(regularizationHours.getRegularisationRequestHours());

			return regularizationHoursDto;
		}

		else {
			return null;
		}
	}

	@Override
	public String getAttendanceAsJson(String managerId, String month) {
		List<Object[]> attendanceData = attendanceRepository.findAttendanceByManagerAndMonth(managerId, month);

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String jsonData = objectMapper.writeValueAsString(attendanceData);
			return jsonData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ManagerAttendanceEditDto editManagerAttendance(ManagerAttendanceEditDto managerAttendanceViewDto,
			String employeeId) {
		LocalDate date = managerAttendanceViewDto.getDate();

		Attendance managerAttendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (managerAttendance != null) {

			managerAttendance.setApprovedHrsForBilling(managerAttendanceViewDto.getApprovedHrsForBilling());
			managerAttendance.setRemarks(managerAttendanceViewDto.getRemarks());

			this.attendanceRepository.save(managerAttendance);

			return managerAttendanceViewDto;
		} else {

			return null;
		}
	}

	@Override

	public String getDuration(String employeeId) {
		if (employeeId == null) {
			return "Employee ID is null";
		}

		LocalDate todaysDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();

		Attendance attendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, todaysDate);

		if (attendance == null) {
			return "No attendance record found for employee ID: " + employeeId;
		}

		LocalTime inTime = attendance.getInTime();
		LocalTime outTime = attendance.getOutTime();
		if (outTime != null) {
			return "Already clocked out";
		}

		Duration duration = Duration.between(inTime, currentTime);

		return Format.getFormattedWorkHours(duration);

	}

	@Override
	public String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplyLeaveDto getLeave(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegularizationHoursDto getRegularizationHours(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillableHoursDto getBillableHours(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagerAttendanceViewDto> findAttendanceByManagerAndMonth(String managerId, String month) {
		try {
			List<Object[]> attendanceData;

			if (month == null) {

				attendanceData = attendanceRepository.findAttendanceByManager(managerId);
			} else {

				attendanceData = attendanceRepository.findAttendanceByManagerAndMonth(managerId, month);
			}

			List<ManagerAttendanceViewDto> attendanceDtoList = new ArrayList<>();
			for (Object[] data : attendanceData) {
				ManagerAttendanceViewDto attendanceDto = new ManagerAttendanceViewDto();

				attendanceDto.setEmployeeId((String) data[0]);
				attendanceDto.setEmployeeName((String) data[1]);
				Department department = mapByteToDepartment((Byte) data[2]);
				attendanceDto.setDepartment(department);
				attendanceDto.setApprovedHoursForBilling((double) data[3]);

				double approvedHoursForBilling = (double) data[3];
				int hours = (int) approvedHoursForBilling;
				double remainingMinutes = (approvedHoursForBilling - hours) * 100;
				int minutes = (int) remainingMinutes;
				int totalMinutes = hours * 60 + minutes;
				attendanceDto.setApprovedHoursForBilling(totalMinutes);

				attendanceDto.setPresentDays((Long) data[4]);
				attendanceDtoList.add(attendanceDto);
			}
			logger.info("attendanceDto :: ", attendanceDtoList);
			return attendanceDtoList;
		} catch (IllegalArgumentException ex) {
			logger.error("Invalid month provided: {}", month);
			return Collections.emptyList();
		} catch (Exception ex) {
			logger.error("An error occurred while fetching attendance data: {}", ex.getMessage(), ex);
			return Collections.emptyList();
		}
	}

	private Department mapByteToDepartment(Byte departmentId) {

		if (departmentId == null) {
			return null;

		}

		int id = departmentId.intValue();
		switch (id) {
		case 0:
			return Department.DIGITAL_FACTORY_SOLUTION;
		case 1:
			return Department.INDUSTRIAL_AUTOMATION_SOLUTION;
		case 2:
			return Department.ENGINEERING_DESIGN_SOLUTION;
		case 3:
			return Department.BUILDING_INFORMATION_MODELING;
		case 4:
			return Department.TALENT_ACQUISITION;
		case 5:
			return Department.HUMAN_RESOURCE;
		case 6:
			return Department.FINANCE;
		case 7:
			return Department.SALES;
		case 8:
			return Department.SYSTEM_ADMIN;
		case 9:
			return Department.INFORMATION_TECHNOLOGY;
		case 10:
			return Department.DIGITAL_MARKETING;
		case 11:
			return Department.DEVELOPMENT;
		default:
			throw new IllegalArgumentException("Invalid Department ID:" + id);
		}

	}

//	@Override
//	
//	    public List<ManagerAttendanceViewDto> findAttendanceByManager(String managerId) {
//		
//		try {
////			Month targetMonth = Month.valueOf(month.toUpperCase());
//
//			List<Object[]> attendanceData = attendanceRepository.findAttendanceByManager(managerId);
//
//			List<ManagerAttendanceViewDto> attendanceDtoList = new ArrayList<>();
//			logger.info("attendanceData :: "+attendanceData);
//			for (Object[] data : attendanceData) {
//				ManagerAttendanceViewDto attendanceDto = new ManagerAttendanceViewDto();
//
//
////				attendanceDto.setMonth(targetMonth);
//				attendanceDto.setEmployeeId((String) data[0]);
//				attendanceDto.setEmployeeName((String) data[1]);
//				Department department = mapByteToDepartment((Byte) data[2]);
//				attendanceDto.setDepartment(department);
//				attendanceDto.setApprovedHoursForBilling((double) data[3]);
//				
//				double approvedHoursForBilling = (double) data[3]; 
//
//			
//				int hours = (int) approvedHoursForBilling; 
//				double remainingMinutes = (approvedHoursForBilling - hours) * 100; 
//				int minutes = (int) remainingMinutes; 
//
//				
//				int totalMinutes = hours * 60 + minutes;
//
//				attendanceDto.setApprovedHoursForBilling(totalMinutes);
//
//
//				attendanceDto.setPresentDays((Long) data[4]);
////				attendanceDto.setMonth(targetMonth);
//				attendanceDtoList.add(attendanceDto);
//			}
//			logger.info("attendanceDto :: ", attendanceDtoList);
//			return attendanceDtoList;
//
//		} catch (IllegalArgumentException ex) {
//
//			return Collections.emptyList();
//		} catch (Exception ex) {
//			logger.error("An error occurred while fetching attendance data: {}", ex.getMessage(), ex);
//			return Collections.emptyList();
//		}
//
// }

	@Override
	public ManagerAttendanceEditDto getManagerAttendance(String employeeId, LocalDate date) {

		Attendance managerAttendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (managerAttendance != null) {
			ManagerAttendanceEditDto managerAttendanceEditDto = new ManagerAttendanceEditDto();
			managerAttendanceEditDto.setApprovedHrsForBilling(managerAttendance.getApprovedHrsForBilling());
			managerAttendanceEditDto.setDate(managerAttendance.getDate());
			managerAttendanceEditDto.setRemarks(managerAttendance.getRemarks());

			return managerAttendanceEditDto;
		} else {

			ManagerAttendanceEditDto messageDto = new ManagerAttendanceEditDto();
			messageDto.setRemarks("No attendance record found for employee ID: " + employeeId + "on Date" + date);
			return messageDto;
		}
	}

	@Override
	public RegularizationManagerEditDto editregularization(RegularizationManagerEditDto regularizationManagerEditDto,
			String employeeId) {
		LocalDate date = regularizationManagerEditDto.getDate();
		Attendance regularizationEdit = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (regularizationEdit != null) {

			regularizationEdit.setInTime(regularizationManagerEditDto.getInTime());
			regularizationEdit.setOutTime(regularizationManagerEditDto.getOutTime());
			regularizationEdit.setDate(regularizationManagerEditDto.getDate());
			regularizationEdit.setRegularisationReason(regularizationManagerEditDto.getRegularisationReason());

			// Set regularisation request hours as work hours
			regularizationEdit.setWorkHrs(regularizationManagerEditDto.getRegularisationRequestHours());

			regularizationEdit.setAttendanceStatus('Y');

			this.attendanceRepository.save(regularizationEdit);

			return regularizationManagerEditDto;
		}

		return null;
	}

	@Override
	public RegularizationManagerEditDto editregularizationreject(
			RegularizationManagerEditDto regularizationManagerEditDto, String employeeId) {
		LocalDate date = regularizationManagerEditDto.getDate();
		Attendance regularizationEdit = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (regularizationEdit != null) {

			regularizationEdit.setInTime(LocalTime.MIN);
			regularizationEdit.setOutTime(LocalTime.MIN);
			regularizationEdit.setWorkHrs(0L);

			regularizationEdit.setDate(regularizationManagerEditDto.getDate());
			regularizationEdit.setRegularisationReason(regularizationManagerEditDto.getRegularisationReason());

			regularizationEdit.setAttendanceStatus('T');

			this.attendanceRepository.save(regularizationEdit);

			return regularizationManagerEditDto;
		}

		return null;
	}

	@Override
	public List<ManagerAttendanceDetailsDto> getAttendance(AttendanceRequestDto attendanceRequestDto) {

		List<ManagerAttendanceDetailsDto> managerAttendanceDetailsDtoList = new ArrayList<>();
		List<Object[]> allAttendanceDetails = new ArrayList<>();

		try {
			Year year = attendanceRequestDto.getYear();
			Month month = attendanceRequestDto.getMonth();
			String employeeId = attendanceRequestDto.getEmployeeId();
			String managerId = attendanceRequestDto.getManagerId();

			if (year == null) {
//			year = LocalDate.now();
				year = Year.now();
			}

			if (month != null) {
//			year = year.withMonth(month.getValue());
				year = Year.now();
			} else {
				month = (LocalDate.now()).getMonth();
			}

//		int _year = year.getYear();
//		int _month = year.getMonthValue();
			int _year = year.getValue();
			int _month = month.getValue();

//		if (employeeId != null && !employeeId.isEmpty()) {
//			allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYearOrEmployeeIdAndYear(managerId, employeeId,
//					_year + "-" + _month);
//		} else if (managerId != null && !managerId.isEmpty()) {
//			allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYearOrEmployeeIdAndYear(managerId, employeeId,
//					_year + "-" + _month);
//		} else {
//			throw new IllegalArgumentException("Both employeeId and managerId cannot be null");
//		}

			if (managerId != null && !managerId.isEmpty()) {
				if (employeeId != null && !employeeId.isEmpty()) {
//				allAttendanceDetails = this.attendanceRepository.findByManagerIdAndYearOrEmployeeIdAndYear(managerId,
//						employeeId, _year + "-" + _month);
				} else {
					allAttendanceDetails = this.attendanceRepository.findByManagerIdAndMonth(managerId, _year, _month);
				}

			}
//		if (employeeId != null && !employeeId.isEmpty()) {
//			if (managerId != null && !managerId.isEmpty()) {
//				allAttendanceDetails = this.attendanceRepository.findByManagerIdAndYearOrEmployeeIdAndYear(managerId,
//						employeeId, _year + "-" + _month);
//			} else {
//				allAttendanceDetails = this.attendanceRepository.findByEmployeeIdAndYear(employeeId,
//						_year + "-" + _month);
//			}
//
//		}

			managerAttendanceDetailsDtoList = processAttendanceDetails(allAttendanceDetails,
					managerAttendanceDetailsDtoList);
		} catch (Exception e) {
			logger.info("Error occurred in getAttendance method: " + e.getMessage(), e);
			throw new ServiceException("Error retrieving attendance", e);
		}
		return managerAttendanceDetailsDtoList;
	}

	private List<ManagerAttendanceDetailsDto> processAttendanceDetails(List<Object[]> allAttendanceDetails,
			List<ManagerAttendanceDetailsDto> managerAttendanceDetailsDtoList) {
		try {
			if (allAttendanceDetails != null) {
				for (Object[] attendanceDetails : allAttendanceDetails) {
//					Employee subDepartmentAndName = this.employeeRepository
//							.findSubDepartmentAndNameByEmployeeId( attendanceDetails.);

					ManagerAttendanceDetailsDto dto = new ManagerAttendanceDetailsDto();
					// dto.setId(attendanceDetails.getId());
					dto.setEmployeeId((String) attendanceDetails[0]);
					dto.setEmployeeName((String) attendanceDetails[1]);
					if (attendanceDetails[2] != null) {
						dto.setDepartment(Departments.Department.values()[(byte) attendanceDetails[2]]);
					}

					dto.setMonth(Month.of((int) attendanceDetails[3]));
					dto.setPresentDays(((Long) attendanceDetails[4]).intValue());
					// dto.setApprovedHrsForBilling(((BigDecimal) attendanceDetails[5]).intValue());

					if (attendanceDetails[5] != null) {
						if (attendanceDetails[5] instanceof BigDecimal) {
							dto.setApprovedHrsForBilling(((BigDecimal) attendanceDetails[5]).intValue());
						} else if (attendanceDetails[5] instanceof Double) {
							dto.setApprovedHrsForBilling(((Double) attendanceDetails[5]).intValue());
						}
					} else {
						dto.setApprovedHrsForBilling(0);
					}

					managerAttendanceDetailsDtoList.add(dto);
				}
			}
		} catch (Exception e) {
			logger.info("Error occurred while processing attendance details: " + e.getMessage(), e);
		}
		return managerAttendanceDetailsDtoList;
	}

}
