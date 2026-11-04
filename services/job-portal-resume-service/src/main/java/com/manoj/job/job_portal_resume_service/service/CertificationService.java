package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.response.CertificationResponse;
import com.manoj.job.job_portal_resume_service.payload.AddCertificationRequest;


import java.util.List;

public interface CertificationService {
    CertificationResponse addCertification(Long resumeId,
                                       Long candidateId,
                                       AddCertificationRequest request) throws Exception;
    List<CertificationResponse> getCertifications(Long resumeId) throws Exception;
    CertificationResponse updateCertification(Long certificationId,
                                      Long resumeId,
                                      Long candidateId,
                                      AddCertificationRequest request) throws Exception;
    void deleteCertification(Long certificationId, Long resumeId, Long candidateId) throws Exception;
}
