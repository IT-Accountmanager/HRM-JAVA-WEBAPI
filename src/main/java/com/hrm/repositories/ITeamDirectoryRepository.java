package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.models.TeamDirectory;

public interface ITeamDirectoryRepository extends JpaRepository<TeamDirectory, Integer> {

}
