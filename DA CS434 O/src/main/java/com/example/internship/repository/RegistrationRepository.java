package com.example.internship.repository;

import com.example.internship.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentIdAndStatus(String studentId, String status);
    boolean existsByCompanyId(Long companyId);
}
