package com.manoj.job_portal_job_service.service.impl;

import com.manoj.job.dto.response.JobTagResponse;
import com.manoj.job_portal_job_service.mapper.JobTagMapper;
import com.manoj.job_portal_job_service.model.JobSkill;
import com.manoj.job_portal_job_service.model.JobTag;
import com.manoj.job_portal_job_service.payload.JobTagRequest;
import com.manoj.job_portal_job_service.repo.JobTagRepo;
import com.manoj.job_portal_job_service.service.JobTagService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobTagServiceImpl implements JobTagService {
    @Autowired
    private JobTagRepo jobTagRepo;


    @Override
    public JobTagResponse createTag(JobTagRequest request) throws Exception {
        if(jobTagRepo.existsByName(request.getName())){
            throw  new Exception("Skill Already Exists");
        }
        String slug = generateSlug(request.getName());
        JobTag tag = JobTag.builder()
                .name(request.getName())
                .slug(slug)
                .build();
        JobTag savedTag  = jobTagRepo.save(tag);
        return JobTagMapper.toResponse(savedTag);
    }

    private String generateSlug(@NotBlank(message = "Tag name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]","")
                .trim()
                .replaceAll("[\\s-]+","");
        if(!jobTagRepo.existsBySlug(base)) return base;
        int counter = 1;
        while(jobTagRepo.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return jobTagRepo.findAll().stream().map(JobTagMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public JobTagResponse getTagById(Long id) throws Exception {
        return JobTagMapper.toResponse(jobTagRepo.findById(id).orElseThrow(() -> new Exception("Job Skill Not Found")));
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception {
        JobTag jobTag  = getTagEntityById(id);
        if(!jobTag.getName().equals(request.getName())
        && jobTagRepo.existsByName(request.getName())){
            throw new Exception("Tag Name already Exists");
        }
        jobTag.setName(request.getName());
        return JobTagMapper.toResponse(jobTagRepo.save(jobTag));
    }

    @Override
    public void delete(Long id) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        jobTagRepo.delete(jobTag);

    }

    @Override
    public JobTag getTagEntityById(Long id) throws Exception {
        return jobTagRepo.findById(id).orElseThrow(() ->new Exception("Tag Not Found"));
    }

    @Override
    public Set<JobTag> getTagsByIds(Set<Long> ids) {
        return new HashSet<>(jobTagRepo.findAll());
    }
}
