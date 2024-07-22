package com.theraconnect.domain.member.repository;

import com.theraconnect.domain.member.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
}
