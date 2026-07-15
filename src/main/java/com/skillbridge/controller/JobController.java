package com.skillbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.entity.Job;
import com.skillbridge.repository.JobRepository;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    // Get all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

// Add new job
@PostMapping
public Job addJob(@RequestBody Job job) {

    if(job.getTitle() == null || job.getTitle().trim().isEmpty() ||
       job.getCompany() == null || job.getCompany().trim().isEmpty() ||
       job.getLocation() == null || job.getLocation().trim().isEmpty() ||
       job.getSkills() == null || job.getSkills().trim().isEmpty()) {

        throw new RuntimeException("All fields are required");
    }

    return jobRepository.save(job);
}
    


    // Get job by id
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobRepository.findById(id).orElse(null);
    }


    // Update job
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {

        Job existingJob = jobRepository.findById(id).orElse(null);

        if(existingJob != null){

            existingJob.setTitle(job.getTitle());
            existingJob.setCompany(job.getCompany());
            existingJob.setLocation(job.getLocation());
            existingJob.setSkills(job.getSkills());

            return jobRepository.save(existingJob);
        }

        return null;
    }


    // Delete job
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {

        jobRepository.deleteById(id);

        return "Job Deleted Successfully";
    }
    // Search jobs by title
@GetMapping("/search")
public List<Job> searchJobs(@RequestParam String title) {

    return jobRepository.findByTitleContainingIgnoreCase(title);

}

}