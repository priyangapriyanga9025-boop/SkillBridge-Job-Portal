package com.skillbridge.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.entity.AppliedJob;
import com.skillbridge.repository.AppliedJobRepository;

@Service
public class AppliedJobService {
     private final AppliedJobRepository repository;

    public AppliedJobService(AppliedJobRepository repository) {
        this.repository = repository;
    }

    public AppliedJob apply(AppliedJob job) {
        return repository.save(job);
    }

    public List<AppliedJob> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public AppliedJob updateStatus(Long id,String status){

    AppliedJob job=repository.findById(id).orElseThrow();

    job.setStatus(status);

    return repository.save(job);

}
// Get applied jobs by student email
public List<AppliedJob> getByStudentEmail(String studentEmail) {
    return repository.findByStudentEmail(studentEmail);
}
}

