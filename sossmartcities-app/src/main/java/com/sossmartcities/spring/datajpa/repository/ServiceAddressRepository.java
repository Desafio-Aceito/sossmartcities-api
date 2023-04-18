package com.sossmartcities.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sossmartcities.spring.datajpa.model.ServiceAddress;

public interface ServiceAddressRepository extends JpaRepository<ServiceAddress, Long> {
}
