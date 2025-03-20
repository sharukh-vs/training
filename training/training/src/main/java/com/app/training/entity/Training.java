package com.app.training.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 10000)
    @Max(value = 99999)
    private Integer id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @ElementCollection
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private Duration duration;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long numberOfBatches;

    private Long numberOfStudentsPerBatch;

    @Enumerated(EnumType.STRING)
    private StudentType studentType;

    private Double budget;

    private String tableOfContent;

    private String pointOfContactName;

    @Email
    private String pointOfContactEmail;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String pointOfContactPhone;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String organizationName;

    public Training() {
    }

    public Training(String name, List<String> skills, Duration duration, LocalDate startDate, LocalDate endDate, Long numberOfBatches, Long numberOfStudentsPerBatch, StudentType studentType, Double budget, String tableOfContent, String pointOfContactName, String pointOfContactEmail, String pointOfContactPhone, Status status, String organizationName) {
        this.name = name;
        this.skills = skills;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfBatches = numberOfBatches;
        this.numberOfStudentsPerBatch = numberOfStudentsPerBatch;
        this.studentType = studentType;
        this.budget = budget;
        this.tableOfContent = tableOfContent;
        this.pointOfContactName = pointOfContactName;
        this.pointOfContactEmail = pointOfContactEmail;
        this.pointOfContactPhone = pointOfContactPhone;
        this.status = status;
        this.organizationName = organizationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Long getNumberOfBatches() {
        return numberOfBatches;
    }

    public void setNumberOfBatches(Long numberOfBatches) {
        this.numberOfBatches = numberOfBatches;
    }

    public Long getNumberOfStudentsPerBatch() {
        return numberOfStudentsPerBatch;
    }

    public void setNumberOfStudentsPerBatch(Long numberOfStudentsPerBatch) {
        this.numberOfStudentsPerBatch = numberOfStudentsPerBatch;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getTableOfContent() {
        return tableOfContent;
    }

    public void setTableOfContent(String tableOfContent) {
        this.tableOfContent = tableOfContent;
    }

    public String getPointOfContactName() {
        return pointOfContactName;
    }

    public void setPointOfContactName(String pointOfContactName) {
        this.pointOfContactName = pointOfContactName;
    }

    public String getPointOfContactEmail() {
        return pointOfContactEmail;
    }

    public void setPointOfContactEmail(String pointOfContactEmail) {
        this.pointOfContactEmail = pointOfContactEmail;
    }

    public String getPointOfContactPhone() {
        return pointOfContactPhone;
    }

    public void setPointOfContactPhone(String pointOfContactPhone) {
        this.pointOfContactPhone = pointOfContactPhone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }



    @PrePersist
    @PreUpdate
    private void updateStatus(){
        LocalDate today = LocalDate.now();
        if(startDate != null && endDate != null){
            if(today.isBefore(startDate.minusDays(7))){
                this.status = Status.UPCOMING;
            }
        } else if(today.isAfter(startDate) && today.isBefore(endDate)){
            this.status = Status.IN_PROGRESS;
        } else if(today.isAfter(endDate)) {
            this.status = Status.COMPLETED;
        } else {
            this.status = Status.SCHEDULED;
        }
    }


}
