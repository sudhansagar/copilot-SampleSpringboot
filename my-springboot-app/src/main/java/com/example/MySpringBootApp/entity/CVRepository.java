package com.example.MySpringBootApp.entity;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CVRepository extends JpaRepository<CVEntity, Long> {
}
