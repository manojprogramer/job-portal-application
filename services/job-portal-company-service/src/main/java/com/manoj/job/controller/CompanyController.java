package com.manoj.job.controller;

import com.manoj.job.domain.CompanyStatus;
import com.manoj.job.domain.CompanyType;
import com.manoj.job.domain.IndustryType;
import com.manoj.job.dto.ApiResponse;
import com.manoj.job.dto.request.CompanyRequest;
import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping("/createcompany")
    public ResponseEntity<CompanyResponse> createCompany(@RequestHeader("X-User-Id") long ownerId,
                                                     @RequestBody @Valid CompanyRequest companyRequest)
            throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(ownerId,companyRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanyById(id));
    }
    @GetMapping("/my")
    public ResponseEntity<CompanyResponse> getMyCompany(@RequestHeader("X-User-Id") Long ownerId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getMyCompany(ownerId));
    }
    @GetMapping("/get")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(@RequestParam(required = false)CompanyType companyType,
                                                                 @RequestParam(required = false)IndustryType industryType,
                                                                 @RequestParam(required = false)CompanyStatus companyStatus){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies(companyType,industryType,companyStatus));
    }
    @PutMapping("update/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable("id") Long companyId,@RequestHeader("X-User-Id") Long ownerId, @RequestBody @Valid CompanyRequest companyRequest) throws Exception {
        System.out.println("Entering into the controller");
        return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(companyId,ownerId,companyRequest));
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.verifyCompany(id));
    }
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponse> deActivateCompany(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.deActivateCompany(id));
    }
    @PatchMapping("/{id}/activate")
    public ResponseEntity<CompanyResponse> activateCompany(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.activateCompany(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable("id") Long companyId,
                                                     @RequestHeader("X-user-Id") Long ownerId) throws Exception {
        companyService.deleteCompany(companyId,ownerId);
        return ResponseEntity.ok(new ApiResponse("Company Deleted Successfully",true));
    }

}
