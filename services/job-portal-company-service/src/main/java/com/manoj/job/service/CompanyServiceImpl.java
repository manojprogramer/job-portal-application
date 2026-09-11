package com.manoj.job.service;

import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.dto.request.CompanyRequest;
import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepo companyRepo;
    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) {
        return null;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return null;
    }

    @Override
    public CompanyResponse getMyCompany(Long id) {
        return null;
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return List.of();
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request) {
        return null;
    }

    @Override
    public CompanyResponse verifyCompany(Long CompanyId) {
        return null;
    }

    @Override
    public void deleteCompany(Long companyId) {

    }

    @Override
    public CompanyResponse deActivateCompany(Long companyId) {
        return null;
    }

    @Override
    public CompanyResponse getCompanyEntityById(Long companyId) {
        return null;
    }
}
