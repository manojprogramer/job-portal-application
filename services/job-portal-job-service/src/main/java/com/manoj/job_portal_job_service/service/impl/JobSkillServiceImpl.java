package com.manoj.job_portal_job_service.service.impl;

import com.manoj.job.dto.response.JobSkillResponse;
import com.manoj.job_portal_job_service.mapper.JobMapper;
import com.manoj.job_portal_job_service.mapper.JobSkillMapper;
import com.manoj.job_portal_job_service.model.JobSkill;
import com.manoj.job_portal_job_service.payload.JobSkillRequest;
import com.manoj.job_portal_job_service.repo.JobSkillRepo;
import com.manoj.job_portal_job_service.service.JobSkillService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobSkillServiceImpl implements JobSkillService {
    @Autowired
    private JobSkillRepo jobSkillRepo;

    @Override
    public JobSkillResponse createSkill(JobSkillRequest request) throws Exception {
        if(jobSkillRepo.existsByName(request.getName())){
            throw new Exception("Category name Already Exists, Choose different Name");
        }
        String slug = generateSlug(request.getName());
        JobSkill skill = JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .category(request.getSkillCategory())
                .build();
        JobSkill saveSkill = jobSkillRepo.save(skill);
        return JobSkillMapper.toResponse(saveSkill);
    }

    private String generateSlug(@NotBlank(message = "Skill is Required") @Size(max = 100, message = "Name must not be exceed 100 characters") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]","")
                .trim()
                .replaceAll("[\\s-]+","-");
        if(!jobSkillRepo.existsBySlug(base)) return base;
        else {
            int counter = 1;
          while(jobSkillRepo.existsBySlug(base+"-"+counter)){
                counter++;
            }
            return base+"-"+counter;
        }
    }

    @Override
    public List<JobSkillResponse> getAllSkills() {
        return jobSkillRepo.findAll().stream().map(JobSkillMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public JobSkillResponse getSkillById(Long id) throws Exception {
        return JobSkillMapper.toResponse(jobSkillRepo.findById(id).orElseThrow(() -> new Exception("Skill Not Found")));
    }

    @Override
    public JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception {
        JobSkill skill = jobSkillRepo.findById(id).orElseThrow(() -> new Exception("Job Skill Not Found"));
        if(!skill.getName().equals(request.getName())
        && jobSkillRepo.existsByName(request.getName())){
            throw new Exception("Skill Name already Exists");
        }
        skill.setName(request.getName());
        skill.setCategory(request.getSkillCategory());
        JobSkill updatedSkill = jobSkillRepo.save(skill);
        return JobSkillMapper.toResponse(updatedSkill);
    }

    @Override
    public void deleteSkill(Long id) throws Exception {
        JobSkill skill = jobSkillRepo.findById(id).orElseThrow(() -> new Exception("Job Skill Not Found"));
        jobSkillRepo.delete(skill);
    }

    @Override
    public Set<JobSkill> getSkillsById(Set<Long> ids) {
        Set<JobSkill> skills = new HashSet<>(jobSkillRepo.findAllById(ids));
        return skills;
    }
}
