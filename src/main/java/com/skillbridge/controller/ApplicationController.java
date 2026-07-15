package com.skillbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.entity.Application;
import com.skillbridge.repository.ApplicationRepository;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {


    private final ApplicationRepository applicationRepository;


    public ApplicationController(ApplicationRepository applicationRepository){

        this.applicationRepository = applicationRepository;

    }


    @GetMapping
    public List<Application> getApplications(){

        return applicationRepository.findAll();

    }
    @PutMapping("/update/{id}")
public Application updateStatus(
        @PathVariable Long id,
        @RequestBody Application application){

    Application existing = applicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));

    existing.setStatus(application.getStatus());

    return applicationRepository.save(existing);
}
@GetMapping("/apply")
public List<Application> getAppliedApplications(){

    return applicationRepository.findAll();

}
@GetMapping("/student/{email}")
public List<Application> getStudentApplications(
        @PathVariable String email){

    return applicationRepository.findByApplicantName(email);

}

}