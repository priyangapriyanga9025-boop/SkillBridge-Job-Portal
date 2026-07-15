package com.skillbridge.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.repository.ApplicationRepository;
import com.skillbridge.repository.JobRepository;
import com.skillbridge.repository.SavedJobRepository;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins="*")
public class StudentController {
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final SavedJobRepository savedJobRepository;


    public StudentController(
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            SavedJobRepository savedJobRepository){

        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.savedJobRepository = savedJobRepository;

    }
     @GetMapping("/stats")
    public Map<String,Object> getStudentStats(){

        Map<String,Object> data = new HashMap<>();

        data.put("availableJobs", jobRepository.count());

        data.put("savedJobs", savedJobRepository.count());

        data.put("appliedJobs", applicationRepository.count());

        return data;
    }

}


