package com.hrm.main.payloads;

import java.time.LocalDate;
import com.hrm.main.models.Helper.EnumCollection.Designation;
import com.hrm.main.models.Helper.EnumCollection.WorkLocation;

public class ReleaseAppointmentLetterDto {
	private long candidateId;
	private String employeeId;
	private String name;
	private String jobTitle;
	private Designation designation;
	private WorkLocation workLocation;
	private LocalDate dateOfJoining;
	private float ctc;
	private float bondPeriod;
	private long bondBreakAmount;
	private String emailId;
	private long contactNumber;
	private double basicSalary;
	private double monthlyBasicSalary;
	private double houseRentAllowance;
	private double monthlyHouseRentAllowance;
	private double medicalAllowance;
	private double monthlyMedicalAllowance;
	private double telephoneAllowance;
	private double monthlyTelephoneAllowance;
	private double internateAllowance;
	private double monthlyInternateAllowance;
	private double leaveTravelAllowance;
	private double monthlyLeaveTravelAllowance;
	private double foodCoupens;
	private double monthlyFoodCoupens;
	private double newsAllowance;
	private double monthlyNewsAllowance;
	private double employerPF;
	private double monthlyEmployerPF;
	private double employeePF;
	private double monthlyEmployeePF;
	private double gratuity;
	private double monthlyGratuity;
	private double groupInsurance;
	private double monthlyGroupInsurance;
	private double professionalTax;
	private double monthlyProfessionalTax;
	private double specialAllowance;
	private double monthlySpecialAllowance;
	private double grossSalary;
	private double monthlyGrossSalary;
	private double netTakeHome;
	private double monthlyNetTakeHome;
	private double totalCTC;
	private double monthlytotalCTC;
	private String error;

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public WorkLocation getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(WorkLocation workLocation) {
		this.workLocation = workLocation;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public float getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(float bondPeriod) {
		this.bondPeriod = bondPeriod;
	}

	public long getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(long bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getMonthlyBasicSalary() {
		return monthlyBasicSalary;
	}

	public void setMonthlyBasicSalary(double monthlyBasicSalary) {
		this.monthlyBasicSalary = monthlyBasicSalary;
	}

	public double getHouseRentAllowance() {
		return houseRentAllowance;
	}

	public void setHouseRentAllowance(double houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}

	public double getMonthlyHouseRentAllowance() {
		return monthlyHouseRentAllowance;
	}

	public void setMonthlyHouseRentAllowance(double monthlyHouseRentAllowance) {
		this.monthlyHouseRentAllowance = monthlyHouseRentAllowance;
	}

	public double getMedicalAllowance() {
		return medicalAllowance;
	}

	public void setMedicalAllowance(double medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}

	public double getMonthlyMedicalAllowance() {
		return monthlyMedicalAllowance;
	}

	public void setMonthlyMedicalAllowance(double monthlyMedicalAllowance) {
		this.monthlyMedicalAllowance = monthlyMedicalAllowance;
	}

	public double getTelephoneAllowance() {
		return telephoneAllowance;
	}

	public void setTelephoneAllowance(double telephoneAllowance) {
		this.telephoneAllowance = telephoneAllowance;
	}

	public double getMonthlyTelephoneAllowance() {
		return monthlyTelephoneAllowance;
	}

	public void setMonthlyTelephoneAllowance(double monthlyTelephoneAllowance) {
		this.monthlyTelephoneAllowance = monthlyTelephoneAllowance;
	}

	public double getInternateAllowance() {
		return internateAllowance;
	}

	public void setInternateAllowance(double internateAllowance) {
		this.internateAllowance = internateAllowance;
	}

	public double getMonthlyInternateAllowance() {
		return monthlyInternateAllowance;
	}

	public void setMonthlyInternateAllowance(double monthlyInternateAllowance) {
		this.monthlyInternateAllowance = monthlyInternateAllowance;
	}

	public double getLeaveTravelAllowance() {
		return leaveTravelAllowance;
	}

	public void setLeaveTravelAllowance(double leaveTravelAllowance) {
		this.leaveTravelAllowance = leaveTravelAllowance;
	}

	public double getMonthlyLeaveTravelAllowance() {
		return monthlyLeaveTravelAllowance;
	}

	public void setMonthlyLeaveTravelAllowance(double monthlyLeaveTravelAllowance) {
		this.monthlyLeaveTravelAllowance = monthlyLeaveTravelAllowance;
	}

	public double getFoodCoupens() {
		return foodCoupens;
	}

	public void setFoodCoupens(double foodCoupens) {
		this.foodCoupens = foodCoupens;
	}

	public double getMonthlyFoodCoupens() {
		return monthlyFoodCoupens;
	}

	public void setMonthlyFoodCoupens(double monthlyFoodCoupens) {
		this.monthlyFoodCoupens = monthlyFoodCoupens;
	}

	public double getNewsAllowance() {
		return newsAllowance;
	}

	public void setNewsAllowance(double newsAllowance) {
		this.newsAllowance = newsAllowance;
	}

	public double getMonthlyNewsAllowance() {
		return monthlyNewsAllowance;
	}

	public void setMonthlyNewsAllowance(double monthlyNewsAllowance) {
		this.monthlyNewsAllowance = monthlyNewsAllowance;
	}

	public double getEmployerPF() {
		return employerPF;
	}

	public void setEmployerPF(double employerPF) {
		this.employerPF = employerPF;
	}

	public double getMonthlyEmployerPF() {
		return monthlyEmployerPF;
	}

	public void setMonthlyEmployerPF(double monthlyEmployerPF) {
		this.monthlyEmployerPF = monthlyEmployerPF;
	}

	public double getEmployeePF() {
		return employeePF;
	}

	public void setEmployeePF(double employeePF) {
		this.employeePF = employeePF;
	}

	public double getMonthlyEmployeePF() {
		return monthlyEmployeePF;
	}

	public void setMonthlyEmployeePF(double monthlyEmployeePF) {
		this.monthlyEmployeePF = monthlyEmployeePF;
	}

	public double getGratuity() {
		return gratuity;
	}

	public void setGratuity(double gratuity) {
		this.gratuity = gratuity;
	}

	public double getMonthlyGratuity() {
		return monthlyGratuity;
	}

	public void setMonthlyGratuity(double monthlyGratuity) {
		this.monthlyGratuity = monthlyGratuity;
	}

	public double getGroupInsurance() {
		return groupInsurance;
	}

	public void setGroupInsurance(double groupInsurance) {
		this.groupInsurance = groupInsurance;
	}

	public double getMonthlyGroupInsurance() {
		return monthlyGroupInsurance;
	}

	public void setMonthlyGroupInsurance(double monthlyGroupInsurance) {
		this.monthlyGroupInsurance = monthlyGroupInsurance;
	}

	public double getProfessionalTax() {
		return professionalTax;
	}

	public void setProfessionalTax(double professionalTax) {
		this.professionalTax = professionalTax;
	}

	public double getMonthlyProfessionalTax() {
		return monthlyProfessionalTax;
	}

	public void setMonthlyProfessionalTax(double monthlyProfessionalTax) {
		this.monthlyProfessionalTax = monthlyProfessionalTax;
	}

	public double getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(double specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public double getMonthlySpecialAllowance() {
		return monthlySpecialAllowance;
	}

	public void setMonthlySpecialAllowance(double monthlySpecialAllowance) {
		this.monthlySpecialAllowance = monthlySpecialAllowance;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public double getMonthlyGrossSalary() {
		return monthlyGrossSalary;
	}

	public void setMonthlyGrossSalary(double monthlyGrossSalary) {
		this.monthlyGrossSalary = monthlyGrossSalary;
	}

	public double getNetTakeHome() {
		return netTakeHome;
	}

	public void setNetTakeHome(double netTakeHome) {
		this.netTakeHome = netTakeHome;
	}

	public double getMonthlyNetTakeHome() {
		return monthlyNetTakeHome;
	}

	public void setMonthlyNetTakeHome(double monthlyNetTakeHome) {
		this.monthlyNetTakeHome = monthlyNetTakeHome;
	}

	public double getTotalCTC() {
		return totalCTC;
	}

	public void setTotalCTC(double totalCTC) {
		this.totalCTC = totalCTC;
	}

	public double getMonthlytotalCTC() {
		return monthlytotalCTC;
	}

	public void setMonthlytotalCTC(double monthlytotalCTC) {
		this.monthlytotalCTC = monthlytotalCTC;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ReleaseAppointmentLetterDto createCtcBreakUp(float CTC) {

		if (CTC >= 900000) {
			basicSalary = CTC * 0.2;
			double monthlySalary = basicSalary / 12.0;
			monthlyBasicSalary = Math.floor(monthlySalary * 100);
			// monthlyBasicSalary = Double.parseDouble(df.format(monthlySalary));

			houseRentAllowance = basicSalary * 0.4;
			monthlyHouseRentAllowance = houseRentAllowance / 12.0;

			medicalAllowance = 24000.0;
			monthlyMedicalAllowance = medicalAllowance / 12.0;

			telephoneAllowance = 12000.0;
			monthlyTelephoneAllowance = telephoneAllowance / 12.0;

			internateAllowance = 30000.0;
			monthlyInternateAllowance = internateAllowance / 12.0;

			leaveTravelAllowance = 60000.0;
			monthlyLeaveTravelAllowance = leaveTravelAllowance / 12.0;

			foodCoupens = 24000.0;
			monthlyFoodCoupens = foodCoupens / 12.0;

			newsAllowance = 18000.0;
			monthlyNewsAllowance = newsAllowance / 12.0;

			employeePF = basicSalary * 0.12;
			monthlyEmployeePF = employeePF / 12.0;

			employerPF = basicSalary * 0.13;
			monthlyEmployerPF = employerPF / 12.0;

			gratuity = basicSalary * 0.0481;
			monthlyGratuity = gratuity / 12.0;

			groupInsurance = 7200.0;
			monthlyGroupInsurance = groupInsurance / 12.0;

			professionalTax = 2400;
			monthlyProfessionalTax = professionalTax / 12.0;

			specialAllowance = CTC - basicSalary - houseRentAllowance - medicalAllowance - telephoneAllowance
					- internateAllowance - leaveTravelAllowance - foodCoupens - newsAllowance - employerPF
					- groupInsurance;
			monthlySpecialAllowance = specialAllowance / 12.0;

			grossSalary = basicSalary + houseRentAllowance + medicalAllowance + telephoneAllowance + internateAllowance
					+ leaveTravelAllowance + foodCoupens + newsAllowance + specialAllowance;
			monthlyGrossSalary = grossSalary / 12.0;

			netTakeHome = grossSalary - employeePF - gratuity - professionalTax;
			monthlyNetTakeHome = netTakeHome / 12.0;

			totalCTC = grossSalary + employerPF + groupInsurance;
			monthlytotalCTC = totalCTC / 12.0;

			return this;
		} else if (CTC >= 180000 && CTC <= 899999) {

			basicSalary = CTC * 0.2;
			monthlyBasicSalary = basicSalary / 12.0;

			houseRentAllowance = basicSalary * 0.4;
			monthlyHouseRentAllowance = houseRentAllowance / 12.0;

			medicalAllowance = 12000.0;
			monthlyMedicalAllowance = medicalAllowance / 12.0;

			telephoneAllowance = 12000.0;
			monthlyTelephoneAllowance = telephoneAllowance / 12.0;

			internateAllowance = 30000.0;
			monthlyInternateAllowance = internateAllowance / 12.0;

			leaveTravelAllowance = 15000.0;
			monthlyLeaveTravelAllowance = leaveTravelAllowance / 12.0;

			foodCoupens = 24000.0;
			monthlyFoodCoupens = foodCoupens / 12.0;

			newsAllowance = 12000.0;
			monthlyNewsAllowance = newsAllowance / 12.0;

			employeePF = basicSalary * 0.12;
			monthlyEmployeePF = employeePF / 12.0;

			employerPF = basicSalary * 0.13;
			monthlyEmployerPF = employerPF / 12.0;

			gratuity = basicSalary * 0.0481;
			monthlyGratuity = gratuity / 12.0;

			groupInsurance = 7200.0;
			monthlyGroupInsurance = groupInsurance / 12.0;

			professionalTax = 1800;
			monthlyProfessionalTax = professionalTax / 12.0;

			specialAllowance = CTC - basicSalary - houseRentAllowance - medicalAllowance - telephoneAllowance
					- internateAllowance - leaveTravelAllowance - foodCoupens - newsAllowance - employerPF
					- groupInsurance;
			monthlySpecialAllowance = specialAllowance / 12.0;

			grossSalary = basicSalary + houseRentAllowance + medicalAllowance + telephoneAllowance + internateAllowance
					+ leaveTravelAllowance + foodCoupens + newsAllowance + specialAllowance;
			monthlyGrossSalary = grossSalary / 12.0;

			netTakeHome = grossSalary - employeePF - gratuity - professionalTax;
			monthlyNetTakeHome = netTakeHome / 12.0;

			totalCTC = grossSalary + employerPF + groupInsurance;
			monthlytotalCTC = totalCTC / 12.0;

			return this;
		}
		return null;
	}

}
