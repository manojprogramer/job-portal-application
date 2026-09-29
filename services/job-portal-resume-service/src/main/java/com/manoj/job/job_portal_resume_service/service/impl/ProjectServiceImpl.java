package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.ProjectResponse;
import com.manoj.job.job_portal_resume_service.mapper.ResumeMapper;
import com.manoj.job.job_portal_resume_service.model.Project;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.payload.AddProjectRequest;
import com.manoj.job.job_portal_resume_service.repo.ProjectRepo;
import com.manoj.job.job_portal_resume_service.service.ProjectService;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ResumeService resumeService;

    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest request) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        Project project = Project.builder()
                .resume(resume)
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies())
                .projectUrl(request.getProjectUrl())
                .sourceCodeUrl(request.getSourceCodeUrl())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isOnGoing(Boolean.TRUE.equals(request.getIsOnGoing()))
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
        Project saved = projectRepo.save(project);
        return ResumeMapper.toProjectResponse(saved);
    }
    public void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("Resume Not Found");
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId).stream()
                .map(ResumeMapper::toProjectResponse).toList();
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest request) throws Exception {
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new Exception("Project Not Found"));
        assertOwner(project.getResume(),candidateId);

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        if(request.getTechnologies() != null) project.setTechnologies(request.getTechnologies());
        project.setProjectUrl(request.getProjectUrl());
        project.setSourceCodeUrl(request.getSourceCodeUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setIsOnGoing(Boolean.TRUE.equals(request.getIsOnGoing()));
        if(request.getDisplayOrder() != null) project.setDisplayOrder(request.getDisplayOrder());

        return ResumeMapper.toProjectResponse(projectRepo.save(project));
    }

    @Override
    public void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception {
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new Exception("Project Not Found"));

        assertOwner(project.getResume(),candidateId);

        projectRepo.delete(project);

    }
}
