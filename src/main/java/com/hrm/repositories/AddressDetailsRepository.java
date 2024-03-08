
package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.AddressDetails;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Integer> {

}
