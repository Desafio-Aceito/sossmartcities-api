package com.sossmartcities.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sossmartcities.spring.datajpa.model.ServiceKind;

public interface ServiceKindRepository extends JpaRepository<ServiceKind, Long> {
}
