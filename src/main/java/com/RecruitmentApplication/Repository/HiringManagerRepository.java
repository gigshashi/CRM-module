package com.RecruitmentApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecruitmentApplication.Entity.NewJobRequestTbl;

@Repository
public interface HiringManagerRepository extends JpaRepository<NewJobRequestTbl,Long> {

}
