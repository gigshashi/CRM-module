package com.RecruitmentApplication.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.RecruitmentApplication.Entity.EmployeeDetailsTbl;
import com.RecruitmentApplication.Entity.NewJobRequestTbl;
import com.RecruitmentApplication.Repository.EmployeeRepository;
import com.RecruitmentApplication.Repository.HiringManagerRepository;
import com.RecruitmentApplication.Service.HiringManagerService;

import jakarta.mail.internet.MimeMessage;

@Service
public class HiringManagerServiceImpl implements HiringManagerService {

	@Value("${spring.mail.username}")
	private String fromEmail;

	private final Logger LOGGER = LoggerFactory.getLogger(HiringManagerServiceImpl.class);

	@Autowired
	private HiringManagerRepository jobRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ResponseEntity<String> createNewJobRequest(NewJobRequestTbl newJobRequest) {
		// Create a new JobDetails entity
		String jobId = generateJobId();
		newJobRequest.setJobId(jobId);

		// added by Vinayak for Sending Mail for Multiple Users
		EmployeeDetailsTbl hemp = employeeRepository.findByEmployeeId(newJobRequest.getCreatedBy());
		EmployeeDetailsTbl memp = employeeRepository.findByEmployeeId(hemp.getManagerId());
		List<String> emailIds = Arrays.asList(hemp.getEmailId(), memp.getEmailId());
		String email = sendMail(emailIds, jobId);
		LOGGER.info("email send Status {}:", email);
		newJobRequest.setStatus("Pending");
		newJobRequest.setCreatedDate(LocalDateTime.now());

		jobRepository.save(newJobRequest);

		return ResponseEntity.status(HttpStatus.OK)
				.body("Your request is sucessfully submited Here is your JobId : " + jobId);
	}

	// added by Sowjanya for Generating JobId
	private String generateJobId() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		sb.append("DES");
		// Generate a random number of specified length
		for (int i = 0; i < 12; i++) {
			int digit = random.nextInt(10);
			sb.append(digit);
		}
		return sb.toString();
	}

	// added by Vinayak for Sending Mail for Multiple Users
	private String sendMail(List<String> emailIds, String jobId) {
		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(emailIds.toArray(new String[0]));
			mimeMessageHelper.setSubject("Job-Openings");
			mimeMessageHelper.setText("One New Job Id was Created And the Job Id is :" + jobId);
			javaMailSender.send(mimeMessage);
			System.out.println("Email sent successfully!");
			return "email send sucussfully";
		} catch (Exception e) {
			return "email not send";
		}

	}
}
