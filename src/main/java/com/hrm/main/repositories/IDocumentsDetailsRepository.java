package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrm.main.models.DocumentDetails;

@Repository
public interface IDocumentsDetailsRepository extends JpaRepository<DocumentDetails, Integer> {
}
