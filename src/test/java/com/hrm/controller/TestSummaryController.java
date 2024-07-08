package com.hrm.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hrm.controllers.SummaryController;
import com.hrm.payloads.EmployeesNameDto;
import com.hrm.services.ISummaryService;

@WebMvcTest(controllers = SummaryController.class)
public class TestSummaryController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ISummaryService summaryService;

	@Test
	public void testGetListOfEmployees_withSearchTerm() throws Exception {
		List<EmployeesNameDto> mockList = Arrays.asList(new EmployeesNameDto("Kartik", "101"),
				new EmployeesNameDto("Akhil", "102"));

		when(summaryService.getListOfEmployeesBySearchTerm("Doe")).thenReturn(mockList);

		mockMvc.perform(get("/import/listOfEmployee").param("searchTerm", "Doe")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("John Doe")).andExpect(jsonPath("$[1].name").value("Jane Doe"));
	}

//	   
}