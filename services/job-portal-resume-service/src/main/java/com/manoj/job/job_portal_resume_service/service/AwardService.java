package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.response.AwardResponse;
import com.manoj.job.job_portal_resume_service.payload.AddAwardRequest;

import java.util.List;

public interface AwardService {
     AwardResponse addAward(Long resumeId, Long candidateId, AddAwardRequest request) throws Exception;
     List<AwardResponse> getAwards(Long resumeId);
     AwardResponse updateAward(Long awardId, Long resumeId,Long candidateId,AddAwardRequest addAwardRequest) throws Exception;
     void deleteAward(Long awardId,Long resumeId,Long candidateId) throws Exception;
}
