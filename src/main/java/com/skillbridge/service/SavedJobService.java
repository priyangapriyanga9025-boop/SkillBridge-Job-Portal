package com.skillbridge.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.entity.SavedJob;
import com.skillbridge.repository.SavedJobRepository;

@Service
public class SavedJobService {
     private final SavedJobRepository savedJobRepository;

    public SavedJobService(SavedJobRepository savedJobRepository) {
        this.savedJobRepository = savedJobRepository;
    }

    // Save Job
    public SavedJob saveJob(SavedJob savedJob) {
        return savedJobRepository.save(savedJob);
    }

    // View Saved Jobs
    public List<SavedJob> getAllSavedJobs() {
        return savedJobRepository.findAll();
    }
     // Delete Saved Job
    public void deleteSavedJob(Long id) {
        savedJobRepository.deleteById(id);
    }
}


