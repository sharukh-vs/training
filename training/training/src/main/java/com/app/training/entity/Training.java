package com.app.training.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
