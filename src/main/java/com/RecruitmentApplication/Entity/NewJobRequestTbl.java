package com.RecruitmentApplication.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_details_tbl")
public class NewJobRequestTbl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String jobId;
	private String jobTitle;
	private String department;
	private Date expectedDateOfJoining;
	private String jobDescription;
	private int numberOfPositions;
	private String experience;
	private Long availableBudget;
	private String createdBy;
	private String status;
	private LocalDateTime createdDate;

}
