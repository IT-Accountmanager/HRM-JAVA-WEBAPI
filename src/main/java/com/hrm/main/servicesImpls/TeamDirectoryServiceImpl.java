package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.TeamDirectory;
import com.hrm.main.repositories.ITeamDirectoryRepository;
import com.hrm.main.services.ITeamDirectoryService;

@Service

public class TeamDirectoryServiceImpl implements ITeamDirectoryService {
	
	@Autowired
	private ITeamDirectoryRepository teamDirectoryRepo;

	@Override
	public String createTeamDirectory(TeamDirectory teamDirectory) {
		try {
			var team = this.teamDirectoryRepo.save(teamDirectory);
			if(team.getId()>0) {			
				return "Team Dirctory is now added " + team.getId();
			}
		}catch (Exception e) {
			e.getMessage();
		}
		return "Team Directory is not added ";
	}

	@Override
	public List<TeamDirectory> getAllTeamDirectory() {
		List<TeamDirectory> allTeam=teamDirectoryRepo.findAll();
		return allTeam;
	}

	@Override
	public String updateTeamDirectory(TeamDirectory teamDirectory, Integer id) {
		try {
			
			if(this.teamDirectoryRepo.existsById(id)) {
			
			teamDirectory.setId(id);
			
			this.teamDirectoryRepo.save(teamDirectory);
			
			return "Id no. " + id + " is updated";
			
			}else {
				return "Id no. " +id+" is does not exists ";
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated";
	}

	@Override
	public String deleteTeamDirectory(Integer id) {
		try {
			teamDirectoryRepo.deleteById(id);
			
			return "Id no. " + id + " is deleted successfully";
		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. "+ id + " is not deleted";
	}

	@Override
	public TeamDirectory getTeamDirectoryById(int id) {
		TeamDirectory directory = teamDirectoryRepo.findById(id).get();
		return directory;
	}

}
