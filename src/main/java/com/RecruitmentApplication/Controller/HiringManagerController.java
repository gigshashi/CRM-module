package com.RecruitmentApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.RecruitmentApplication.Entity.NewJobRequestTbl;
import com.RecruitmentApplication.Exception.RecruitmentException;
import com.RecruitmentApplication.Service.HiringManagerService;

@RestController
@RequestMapping("/api/v1.0/hiring")
@CrossOrigin(origins = "*")
public class HiringManagerController {

	@Autowired
	private HiringManagerService hmService;

	// added by Sowjanya for Creating new Job Request
	@PostMapping("/jobRequest")
	public ResponseEntity<?> createNewJobRequest(@RequestBody NewJobRequestTbl newJobRequest) {
		try {
			ResponseEntity<?> jobId = hmService.createNewJobRequest(newJobRequest);
			return jobId;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please try after some times!");
		}
	}
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public String GetAuthorizedException(RecruitmentException errorMsg) {
	return errorMsg.getMessage();
}
}
