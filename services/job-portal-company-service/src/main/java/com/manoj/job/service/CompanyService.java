package com.manoj.job.service;

import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.dto.request.CompanyRequest;
import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse getMyCompany(Long id) throws Exception;
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus);
    CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse verifyCompany(Long CompanyId) throws Exception;
    void deleteCompany(Long companyId, Long ownerId) throws Exception;
    CompanyResponse deActivateCompany(Long companyId) throws Exception;
    CompanyResponse activateCompany(Long companyId) throws Exception;

    Company getCompanyEntityById(Long companyId) throws Exception;
}
