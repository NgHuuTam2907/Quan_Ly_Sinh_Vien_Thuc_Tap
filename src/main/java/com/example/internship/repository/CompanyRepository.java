package com.example.internship.repository;

import com.example.internship.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByNameContainingIgnoreCaseOrIndustryContainingIgnoreCase(String name, String industry);
}
