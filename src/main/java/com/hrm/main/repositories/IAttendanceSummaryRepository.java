package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.AttendanceSummary;

@Repository
public interface IAttendanceSummaryRepository extends JpaRepository<AttendanceSummary, Integer> {

}
