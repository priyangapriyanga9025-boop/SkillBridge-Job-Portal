package com.skillbridge.service;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.skillbridge.repository.ApplicationRepository;
import com.skillbridge.repository.JobRepository;
import com.skillbridge.repository.ResumeRepository;
import com.skillbridge.repository.UserRepository;

@Service
public class DashboardService {
     private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final ResumeRepository resumeRepository;


    public DashboardService(
            UserRepository userRepository,
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            ResumeRepository resumeRepository){

        this.userRepository = userRepository;
         this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.resumeRepository = resumeRepository;
    }


    public Map<String, Long> getDashboardStats(){

        Map<String, Long> stats = new HashMap<>();

        stats.put("users", userRepository.count());
        stats.put("jobs", jobRepository.count());
        stats.put("applications", applicationRepository.count());
        stats.put("resumes", resumeRepository.count());

        return stats;
    }
}