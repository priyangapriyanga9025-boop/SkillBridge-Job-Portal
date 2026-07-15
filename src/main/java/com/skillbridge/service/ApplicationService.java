package com.skillbridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.entity.Application;
import com.skillbridge.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // Apply for a job
    public Application applyJob(Application application) {
        application.setStatus("Applied");
        return applicationRepository.save(application);
    }

    // Get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Get application count
    public long getApplicationCount() {
        return applicationRepository.count();
    }

    // Get application by ID
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    // Save updated application
    public Application save(Application application) {
        return applicationRepository.save(application);
    }
    // Get applications by student email
public List<Application> getApplicationsByApplicantName(String applicantName) {
    return applicationRepository.findByApplicantName(applicantName);
}
}
