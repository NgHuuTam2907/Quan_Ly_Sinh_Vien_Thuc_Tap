package com.example.internship.model;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String industry;

    @Column(nullable = false)
    private String address;

    private Integer positions;

    public Company() {}

    public Company(String name, String industry, String address, Integer positions) {
        this.name = name;
        this.industry = industry;
        this.address = address;
        this.positions = positions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getPositions() { return positions; }
    public void setPositions(Integer positions) { this.positions = positions; }
}
