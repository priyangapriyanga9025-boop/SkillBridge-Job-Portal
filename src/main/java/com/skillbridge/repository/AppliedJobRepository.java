package com.skillbridge.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.entity.AppliedJob;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {
 List<AppliedJob> findByStudentEmail(String studentEmail);
}