package com.RecruitmentApplication.Service;

import org.springframework.http.ResponseEntity;

import com.RecruitmentApplication.Entity.NewJobRequestTbl;

public interface HiringManagerService {

	ResponseEntity<?> createNewJobRequest(NewJobRequestTbl newJobRequest);
}
