package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.TeamDirectory;

public interface ITeamDirectoryRepository extends JpaRepository<TeamDirectory, Integer> {

}
