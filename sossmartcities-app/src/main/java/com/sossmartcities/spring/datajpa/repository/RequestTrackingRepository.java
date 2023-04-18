package com.sossmartcities.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sossmartcities.spring.datajpa.model.RequestTracking;

public interface RequestTrackingRepository extends JpaRepository<RequestTracking, Long> {
}
