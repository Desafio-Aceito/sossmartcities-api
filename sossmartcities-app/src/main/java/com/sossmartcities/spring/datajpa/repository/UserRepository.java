package com.sossmartcities.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sossmartcities.spring.datajpa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
