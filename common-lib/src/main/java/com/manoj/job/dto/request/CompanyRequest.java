package com.manoj.job.dto.request;

import com.manoj.job.domain.CompanySize;
import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.dto.response.SocialLinkResponse;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequest {
    @NotBlank(message = "company name is required")
    private String name;

    private String tagLine;
    private String description;


    private String logoUrl;
    private String coverImageUrl;

    @Pattern(regexp = "^(https?://).*",message = "Website Must be valid Url")
    private String website;

    @Email(message = "Email must Be valid")
    private String email;

    @Min(value = 1800, message = "Founded Year Seems to be Old")
    @Max(value = 2200, message = "Found Year is Invalid")
    private String foundedYear;


    private String phone;

    @NotNull(message = "Company Type is required")
    private CompanyType companyType;

    @NotNull(message = "Company Status is Required")
    private CompanyStatus companyStatus;

    @NotNull(message = "Company Size is Required")
    private CompanySize companySize;

    private String registrationNumber;

    @NotNull(message = "Industry Type is Required")
    private IndustryType industryType;

    private List<SocialLinkResponse> socialLinks;
}
