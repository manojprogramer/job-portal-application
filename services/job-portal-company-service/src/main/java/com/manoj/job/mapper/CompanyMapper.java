package com.manoj.job.mapper;

import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.dto.response.SocialLinkResponse;
import com.manoj.job.model.Company;
import com.manoj.job.model.SocialLink;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {
    public static SocialLinkResponse toSocialLinkResponse(SocialLink socialLink){
        return SocialLinkResponse.builder()
                .url(socialLink.getUrl()).socialPlatform(socialLink.getSocialPlatform()).build();
    }
    public static CompanyResponse toResponse(Company company) {
        List<SocialLinkResponse> socialLinkResponses = company.getSocialLink() == null ? Collections.emptyList()
                : company.getSocialLink().stream().map(CompanyMapper::toSocialLinkResponse).toList();
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagLine(company.getTagLine())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .companyStatus(company.getStatus())
                .active(company.getActive())
                .ownerId(company.getOwnerId())
                .socialLinks(socialLinkResponses)
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }
    public static List<CompanyResponse> toResponseList(List<Company> companies){
        return companies.stream().map(CompanyMapper::toResponse).collect(Collectors.toList());
    }
}
