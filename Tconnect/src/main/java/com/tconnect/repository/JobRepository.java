package com.tconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tconnect.dto.ApplicationStatus;
import com.tconnect.entity.Job;


public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j JOIN j.applicants a WHERE a.applicantId = :applicantId AND a.applicationStatus = :applicationStatus")
    List<Job> findByApplicantIdAndApplicationStatus(@Param("applicantId") Long applicantId,
                                                    @Param("applicationStatus") ApplicationStatus applicationStatus);

    List<Job> findByPostedBy(Long postedBy);
}

