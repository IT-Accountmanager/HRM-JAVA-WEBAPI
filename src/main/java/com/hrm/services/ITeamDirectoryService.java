package com.hrm.services;

import java.util.List;

import com.hrm.models.TeamDirectory;

public interface ITeamDirectoryService {
	
	String createTeamDirectory(TeamDirectory teamDirectory);
	
	List<TeamDirectory>getAllTeamDirectory();
	
	TeamDirectory getTeamDirectoryById(int id);
	
	String updateTeamDirectory(TeamDirectory teamDirectory, Integer id);
	
	String deleteTeamDirectory(Integer id);
	
}
