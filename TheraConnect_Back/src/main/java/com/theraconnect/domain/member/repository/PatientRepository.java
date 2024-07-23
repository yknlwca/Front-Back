package com.theraconnect.domain.member.repository;

import com.theraconnect.domain.member.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
