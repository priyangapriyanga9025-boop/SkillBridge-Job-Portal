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
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.entity.AppliedJob;
import com.skillbridge.service.AppliedJobService;

@RestController
@RequestMapping("/appliedjobs")
@CrossOrigin(origins = "*")
public class AppliedJobController {
     private final AppliedJobService service;

    public AppliedJobController(AppliedJobService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public AppliedJob applyJob(@RequestBody AppliedJob job) {
        return service.apply(job);
    }

    @GetMapping("/all")
    public List<AppliedJob> getAllAppliedJobs() {
        return service.getAll();
    }
    @GetMapping("/student/{email}")
public List<AppliedJob> getStudentAppliedJobs(@PathVariable String email) {
    return service.getByStudentEmail(email);
}

    @DeleteMapping("/delete/{id}")
    public void deleteAppliedJob(@PathVariable Long id) {
        service.delete(id);
}
@PutMapping("/status/{id}")
public AppliedJob updateStatus(@PathVariable Long id,
@RequestBody AppliedJob job){

    return service.updateStatus(id, job.getStatus());

}

}

