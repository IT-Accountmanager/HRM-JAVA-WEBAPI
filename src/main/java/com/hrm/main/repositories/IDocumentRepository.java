package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrm.main.models.Document;

@Repository
public interface IDocumentRepository extends JpaRepository<Document, Integer> {

}
