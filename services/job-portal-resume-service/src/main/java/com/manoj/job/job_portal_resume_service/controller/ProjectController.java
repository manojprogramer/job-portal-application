package com.manoj.job.job_portal_resume_service.controller;


import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.ProjectResponse;
import com.manoj.job.job_portal_resume_service.payload.AddProjectRequest;
import com.manoj.job.job_portal_resume_service.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/add-project")
    public ResponseEntity<ProjectResponse> addProject(@PathVariable("resume-id") Long resumeId,
                                                      @RequestHeader("X-User-Id") Long candidateId,
                                                      @RequestBody @Valid AddProjectRequest request) throws Exception {
        return ResponseEntity.ok(projectService.addProject(resumeId, candidateId, request))
    }
    @GetMapping("/get-projects")
    public ResponseEntity<List<ProjectResponse>>getProjects(@PathVariable("resume-id") Long resumeId){
        return ResponseEntity.ok(projectService.getAllProjects(resumeId));
    }
    @PutMapping("update/{project-id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable("resume-id") Long resumeId,
                                                         @PathVariable("project-id") Long projectId,
                                                         @RequestHeader("X-User-Id") Long candidateId,
                                                         @RequestBody @Valid AddProjectRequest request) throws Exception {
        return ResponseEntity.ok(projectService.updateProject(projectId,resumeId,candidateId,request));
    }
    @DeleteMapping("/delete/{project-id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Long resumeId,
                                                     @PathVariable Long projectId,
                                                     @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        projectService.deleteProject(projectId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Project Deleted Successfully", true));8
    }
}
