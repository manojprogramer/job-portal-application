package com.manoj.job_portal_job_service.controller;

import com.manoj.job.dto.ApiResponse;
import com.manoj.job.dto.response.JobSkillResponse;
import com.manoj.job_portal_job_service.payload.JobSkillRequest;
import com.manoj.job_portal_job_service.service.JobSkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-skill")
public class JobSkillController {
    @Autowired
    private JobSkillService jobSkillService;

    @PostMapping("/create")
    public ResponseEntity<JobSkillResponse> createJobSkill(@RequestBody @Valid JobSkillRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSkillService.createSkill(request));
    }
    @GetMapping("/get-all-skills")
    public ResponseEntity<List<JobSkillResponse>> getAllSkills(){
        return ResponseEntity.ok(jobSkillService.getAllSkills());
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getSkillById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateSkill(@PathVariable Long id, @RequestBody @Valid JobSkillRequest request) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateSkill(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSkill(@PathVariable Long id) throws Exception {
        jobSkillService.deleteSkill(id);
        return ResponseEntity.ok(new ApiResponse("Job Skill deleted Successfully",true));
    }


}
