package com.skillbridge.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

       Optional<Resume> findByEmail(String email);
  Resume findByFileName(String fileName);

    
}

