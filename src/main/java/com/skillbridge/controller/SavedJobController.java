package com.skillbridge.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.entity.SavedJob;
import com.skillbridge.service.SavedJobService;

@RestController
@RequestMapping("/savedjobs")
@CrossOrigin(origins = "*")
public class SavedJobController {
     private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    @PostMapping("/save")
    public SavedJob saveJob(@RequestBody SavedJob savedJob) {
        return savedJobService.saveJob(savedJob);
    }

    @GetMapping("/all")
    public List<SavedJob> getAllSavedJobs() {
        return savedJobService.getAllSavedJobs();
    }
     @DeleteMapping("/delete/{id}")
    public void deleteSavedJob(@PathVariable Long id) {
        savedJobService.deleteSavedJob(id);
    }
}

