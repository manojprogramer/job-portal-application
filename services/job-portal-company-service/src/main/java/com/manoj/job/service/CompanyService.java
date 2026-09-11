package com.manoj.job.service;

import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.dto.request.CompanyRequest;
import com.manoj.job.dto.response.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    CompanyResponse createCompany(Long ownerId, CompanyRequest request);
    CompanyResponse getCompanyById(Long id);
    CompanyResponse getMyCompany(Long id);
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus);
    CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request);
    CompanyResponse verifyCompany(Long CompanyId);
    void deleteCompany(Long companyId);
    CompanyResponse deActivateCompany(Long companyId);

    CompanyResponse getCompanyEntityById(Long companyId);
}
