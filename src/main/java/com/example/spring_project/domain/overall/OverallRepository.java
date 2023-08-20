package com.example.spring_project.domain.overall;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OverallRepository extends JpaRepository<Overall, Long> {
    Overall findByEmail(String email);
}
