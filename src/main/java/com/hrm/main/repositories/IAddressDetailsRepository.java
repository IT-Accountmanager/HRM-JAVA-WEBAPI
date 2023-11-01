
package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.AddressDetails;

@Repository
public interface IAddressDetailsRepository extends JpaRepository<AddressDetails, Integer> {

}
