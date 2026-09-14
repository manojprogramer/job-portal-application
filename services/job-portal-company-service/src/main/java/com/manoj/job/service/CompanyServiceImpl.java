package com.manoj.job.service;

import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.dto.request.CompanyRequest;
import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.dto.response.SocialLinkResponse;
import com.manoj.job.mapper.CompanyMapper;
import com.manoj.job.model.Company;
import com.manoj.job.model.SocialLink;
import com.manoj.job.repo.CompanyRepo;
import jakarta.validation.constraints.NotBlank;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepo companyRepo;
    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception {
        if(companyRepo.existsByOwnerId(ownerId)){
            throw new Exception("Yor Already have company Registered");
        }
        if(companyRepo.existsByName(request.getName()))
            throw new Exception("Company Already Exists");
        if(request.getRegistrationNumber() != null && companyRepo.existsByRegistrationNumber(request.getRegistrationNumber()))
            throw new Exception("Company Already Exists");
        String slug = generateSlug(request.getName());
        Company company = new Company();
        company.setCompanyType(request.getCompanyType());
        company.setCompanySize(request.getCompanySize());
        company.setName(request.getName());
        company.setCoverImageUrl(request.getCoverImageUrl());
        company.setFoundedYear(request.getFoundedYear());
        company.setDescription(request.getDescription());
        company.setLogoUrl(request.getLogoUrl());
        company.setIndustryType(request.getIndustryType());
        company.setOwnerId(ownerId);
        company.setRegistrationNumber(request.getRegistrationNumber());
        company.setName(request.getName());
        company.setSlug(slug);
        company.setTagLine(request.getTagLine());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setSocialLink(mapSocialLinks(request.getSocialLinks()));
        Company c = companyRepo.save(company);
        return CompanyMapper.toResponse(c);
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinks) {
        if(socialLinks == null || socialLinks.isEmpty()) return new ArrayList<>();
        return socialLinks.stream()
                .map(e -> SocialLink.builder().socialPlatform(e.getSocialPlatform()).url(e.getUrl()).build())
                .collect(Collectors.toList());
    }

    private String generateSlug(@NotBlank(message = "company name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]","")
                .trim()
                .replaceAll("\\s-","");
        if(!companyRepo.existsBySlug(base))
            return base;
        int counter = 1;
        while(companyRepo.existsBySlug(base+"-"+counter))
            counter++;
        return base+"-"+counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company c = companyRepo.findById(id).orElseThrow(()-> new Exception("Company Not Found By Id"));
        return CompanyMapper.toResponse(c);
    }

    @Override
    public CompanyResponse getMyCompany(Long id) throws Exception {
        Company c = companyRepo.findByOwnerId(id).orElseThrow(()->new Exception("Company not Found By this Owner Id"));
        return CompanyMapper.toResponse(c);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        List<Company> c = companyRepo.findByFilters(companyType,industryType,companyStatus);
        return CompanyMapper.toResponseList(c);
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request) throws Exception {
        Company company = getCompanyEntityById(companyId);
        if(!company.getName().equals(request.getName() )&& companyRepo.existsByName(request.getName()))
            throw new Exception("Company Name Already Exists");
        if(request.getRegistrationNumber() != null &&
                !request.getRegistrationNumber().equals(company.getRegistrationNumber()) &&
                companyRepo.existsByRegistrationNumber(request.getRegistrationNumber()))
            throw new Exception("Company Registration Number Already Exists");
        company.setName(request.getName());
        company.setTagLine(request.getTagLine());
        company.setDescription(request.getDescription());
        company.setLogoUrl(request.getLogoUrl());
        company.setCoverImageUrl(request.getCoverImageUrl());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setFoundedYear(request.getFoundedYear());
        company.setCompanySize(request.getCompanySize());
        company.setCompanyType(request.getCompanyType());
        company.setIndustryType(request.getIndustryType());
        company.setRegistrationNumber(request.getRegistrationNumber());
        company.setSocialLink(mapSocialLinks(request.getSocialLinks()));
        return CompanyMapper.toResponse(companyRepo.save(company));
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company c = getCompanyEntityById(companyId);
        c.setStatus(CompanyStatus.ACTIVE);
        c.setIsVerified(true);
        return CompanyMapper.toResponse(c);
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company c = getCompanyEntityById(companyId);
        assertOwner(c,ownerId);
        companyRepo.delete(c);
    }

    private void assertOwner(Company company, Long ownerId) throws Exception {
        if(!company.getOwnerId().equals(ownerId))
            throw new Exception("You are not the owner of Company");
    }


    @Override
    public CompanyResponse deActivateCompany(Long companyId) throws Exception {
        Company c = getCompanyEntityById(companyId);
        c.setStatus(CompanyStatus.SUSPENDED);
        c.setIsVerified(false);
        return CompanyMapper.toResponse(c);
    }

    @Override
    public CompanyResponse activateCompany(Long companyId) throws Exception {
        Company c = getCompanyEntityById(companyId);
        c.setStatus(CompanyStatus.ACTIVE);
        c.setIsVerified(false);
        return CompanyMapper.toResponse(c);
    }

    @Override
    public Company getCompanyEntityById(Long companyId) throws Exception {
        return companyRepo.findById(companyId).orElseThrow(()-> new Exception("Company Not Found By Id"));
    }
}
