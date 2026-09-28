package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.JobCategoryResponse;
import com.manoj.job.dto.response.ResumeSkillResponse;
import com.manoj.job.job_portal_resume_service.payload.AddResumeSkillRequest;
import com.manoj.job.job_portal_resume_service.service.ResumeSkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/skills")
public class ResumeSkillController {
    @Autowired
    private ResumeSkillService resumeSkillService;

    @PostMapping("/add-skill")
    public ResponseEntity<ResumeSkillResponse> addSkill(@PathVariable Long resumeId,
                                                                            @RequestHeader("X-User-Id") Long candidateId,
                                                                            @RequestBody @Valid AddResumeSkillRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resumeSkillService.addSkill(resumeId,candidateId,request));
    }
    @GetMapping("/get-skills")
    public ResponseEntity<List<ResumeSkillResponse>> getSkills(@PathVariable Long resumeId){
        return ResponseEntity.ok(resumeSkillService.getSkills(resumeId));
    }
    @PutMapping("/{skill-id}")
    public ResponseEntity<ResumeSkillResponse> updateSkill(@PathVariable Long resumeId,
                                                           @PathVariable Long skillId,
                                                           @RequestHeader("X-User-Id") Long candidateId,
                                                           @RequestBody @Valid AddResumeSkillRequest request) throws Exception {
        return ResponseEntity.ok(resumeSkillService.updateSkill(skillId,resumeId,candidateId,request));
    }
    @DeleteMapping("/{skill-id}")
    public ResponseEntity<ApiResponse> deleteSkills(@PathVariable Long resumeId,
                                                    @PathVariable Long skillId,
                                                    @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        resumeSkillService.deleteSkill(skillId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Skill deleted Successfully",true));
    }

}
