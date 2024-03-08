package com.hrm.controllers;

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

import com.hrm.models.TeamDirectory;
import com.hrm.services.ITeamDirectoryService;
@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Team")

public class TeamDirectoryController {
	
	@Autowired
	ITeamDirectoryService teamDirectoryService;
	
	@PostMapping("/addDirectory")
	public ResponseEntity<String> createTeamDirectory(@RequestBody TeamDirectory teamDirectory){
		
		String result = this.teamDirectoryService.createTeamDirectory(teamDirectory);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);		
		
	}	
	@GetMapping("/getAllDirectory")
	public ResponseEntity<List<TeamDirectory>> getAllTeamDirectory() {
		List<TeamDirectory> allTeam = this.teamDirectoryService.getAllTeamDirectory();
		return new ResponseEntity<List<TeamDirectory>>(allTeam, HttpStatus.OK);
	}
	
	@PutMapping("/updateDirectory/{id}")
	public ResponseEntity<String> updateTeamDirectory(@RequestBody TeamDirectory teamDirectory,@PathVariable Integer id) {
		String result = this.teamDirectoryService.updateTeamDirectory(teamDirectory, id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteDirectory/{id}")
	public ResponseEntity<String> deleteTeamDirectory(@PathVariable Integer id) {
		String result = this.teamDirectoryService.deleteTeamDirectory(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getDirectoryById/{id}")
	public TeamDirectory getDirectoryById(@PathVariable int id) {
		TeamDirectory directory = teamDirectoryService.getTeamDirectoryById(id);
		return directory;
	}
	
}
