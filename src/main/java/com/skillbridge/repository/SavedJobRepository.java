package com.skillbridge.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.entity.SavedJob;

public interface SavedJobRepository extends JpaRepository<SavedJob,Long> {
    
}
