package com.example.MySpringBootApp.repository;

import org.springframework.stereotype.Repository;

import com.example.MySpringBootApp.entity.CVEntity;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CVRepository extends JpaRepository<CVEntity, Long> {
}
