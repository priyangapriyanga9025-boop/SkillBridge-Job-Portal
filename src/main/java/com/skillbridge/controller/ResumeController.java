package com.skillbridge.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skillbridge.entity.Resume;
import com.skillbridge.repository.ResumeRepository;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
private final ResumeRepository resumeRepository;

public ResumeController(ResumeRepository resumeRepository) {
    this.resumeRepository = resumeRepository;
}
    
   
    // Upload Resume
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a PDF file.");
        }

        try {

            File uploadFolder = new File(UPLOAD_DIR);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = file.getOriginalFilename();

            File destination = new File(uploadFolder, fileName);

            file.transferTo(destination);

            // Save Resume Details in Database
            Resume resume = new Resume();
            resume.setName(name);
            resume.setEmail(email);
            resume.setFileName(fileName);
            resume.setFileType(file.getContentType());
            resume.setFilePath(UPLOAD_DIR + fileName);

            resumeRepository.save(resume);

            return ResponseEntity.ok("Resume uploaded successfully.");

        } catch (IOException e) {

            return ResponseEntity.status(500)
                    .body("Error : " + e.getMessage());

        }
    }

    // Download Resume
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadResume(@PathVariable String fileName) {

        try {

            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + fileName + "\"")
                        .body(resource);

            } else {

                return ResponseEntity.notFound().build();

            }

        } catch (IOException e) {

            return ResponseEntity.status(500).build();

        }

    }

    // List Uploaded Files
   @GetMapping("/list")
 public List<Resume> getResumeList() {
    return resumeRepository.findAll();
}

    // Delete Resume
   @DeleteMapping("/delete/{fileName}")
public ResponseEntity<String> deleteResume(@PathVariable String fileName) {

    File file = new File(UPLOAD_DIR + fileName);

    if (file.exists()) {
        file.delete();
    }

    Resume resume = resumeRepository.findByFileName(fileName);

    if (resume != null) {
        resumeRepository.delete(resume);
    }

    return ResponseEntity.ok("Resume deleted successfully");
}
}