package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.BasicInfo;
import com.hrm.main.services.IBasicInfoService;

@RestController
@RequestMapping("/basic")

public class BasicInfoController {
	
	@Autowired
	IBasicInfoService basicInfoService;
	
	@PostMapping("/info")
	public ResponseEntity<String> createBasicInfo(@RequestBody BasicInfo basicInfo){
		String result = this.basicInfoService.createBasicInfo(basicInfo);
		return new ResponseEntity<String> (result,HttpStatus.OK);
				
	}
	@GetMapping("/getInformation")	
	public ResponseEntity<List<BasicInfo>> getAllBasicInfo(){
		List<BasicInfo> allBasicInfo = this.basicInfoService.getAllBasicInfo();
		return new ResponseEntity<List<BasicInfo>>(allBasicInfo,HttpStatus.OK);
	}

}
