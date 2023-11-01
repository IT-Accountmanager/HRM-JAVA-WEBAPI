package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.DocumentUpload;

@Repository
public interface IDocumentUploadRepository extends JpaRepository<DocumentUpload, Integer> {

}
