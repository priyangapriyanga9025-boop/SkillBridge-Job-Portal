package com.skillbridge.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.entity.Job;
import com.skillbridge.repository.JobRepository;

@Service
public class JobService {
     private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }
 public List<Job> getAllJobs() {
        return jobRepository.findAll();
 }
public List<Job> searchJobs(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }
    public long getJobCount() {
    return jobRepository.count();
}
public Job getJobById(Long id) {
    return jobRepository.findById(id).orElse(null);
}

public Job updateJob(Long id, Job job) {

    Job existingJob = jobRepository.findById(id).orElse(null);

    if (existingJob != null) {

        existingJob.setTitle(job.getTitle());
        existingJob.setCompany(job.getCompany());
        existingJob.setLocation(job.getLocation());
        existingJob.setSkills(job.getSkills());

        return jobRepository.save(existingJob);
    }

    return null;
}
public void deleteJob(Long id) {
    jobRepository.deleteById(id);
}
}