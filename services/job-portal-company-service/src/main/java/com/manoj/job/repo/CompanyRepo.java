package com.manoj.job.repo;

import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
    Optional<Company> findByOwnerId(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);

    @Query("SELECT c FROM Company c WHERE (:companyType is NUll OR c.companyType = :companyType) AND" +
            " (:industryType is NULL OR c.industryType = :industryType) AND" +
            "(:status is NULL OR c.status = :status)")
    List<Company> findByFilters(
            @Param("companyType")CompanyType companyType,
            @Param("industryType")IndustryType industryType,
            @Param("status")CompanyStatus status
            );
}
