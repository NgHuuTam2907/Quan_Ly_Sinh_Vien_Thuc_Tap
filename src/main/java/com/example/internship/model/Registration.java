package com.example.internship.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String studentClass;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String positionTitle;

    @Column(nullable = false)
    private String cvUrl;

    private String aspiration;

    private String status = "Chờ duyệt";

    public Registration() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getStudentClass() { return studentClass; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getPositionTitle() { return positionTitle; }
    public void setPositionTitle(String positionTitle) { this.positionTitle = positionTitle; }

    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }

    public String getAspiration() { return aspiration; }
    public void setAspiration(String aspiration) { this.aspiration = aspiration; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
