package com.sossmartcities.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sossmartcities.spring.datajpa.model.RequestedService;

public interface RequestedServiceRepository extends JpaRepository<RequestedService, Long> {
}
