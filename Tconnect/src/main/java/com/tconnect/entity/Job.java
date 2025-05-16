package com.tconnect.entity;

import java.time.LocalDateTime;
import java.util.List;



import com.tconnect.dto.JobDTO;
import com.tconnect.dto.JobStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Applicant> applicants;

    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered;

    private LocalDateTime postTime;
    private String description;

    @ElementCollection
    private List<String> skillsRequired;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    private Long postedBy;

    public JobDTO toDTO() {
        return new JobDTO(
            this.id,
            this.jobTitle,
            this.company,
            this.applicants != null ? this.applicants.stream().map(Applicant::toDTO).toList() : null,
            this.about,
            this.experience,
            this.jobType,
            this.location,
            this.packageOffered,
            this.postTime,
            this.description,
            this.skillsRequired,
            this.jobStatus,
            this.postedBy
        );
    }
}
