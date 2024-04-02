
package com.hrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.AddressDetails;

@Repository
public interface IAddressDetailsRepository extends JpaRepository<AddressDetails, Integer> {

//	List<AddressDetails> findByCandidateId(long candidateId);

}
