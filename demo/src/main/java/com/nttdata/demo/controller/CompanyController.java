package com.nttdata.demo.controller;

import com.nttdata.demo.entity.Company;
import com.nttdata.demo.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company-service")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create-company")
    public Company saveOrUpdate(@RequestBody Company company){
        return companyService.saveOrUpdateCompany(company);
    }

    @PutMapping("/update-company")
    public Company updateCompany(@RequestBody Company company){
        return companyService.saveOrUpdateCompany(company);
    }

    @DeleteMapping("/delete-company/{vatNumber}")
    public String deleteCompany(@PathVariable String vatNumber){
        return companyService.deleteCompany(vatNumber).get(false);
    }

    @GetMapping("/get-company/{vatNumber}")
    public Company getCompany(@PathVariable String vatNumber){
        return companyService.getCompany(vatNumber);
    }

    @GetMapping("/get-all-companies")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping("/createCompanyValidated")
    public Company saveValidatedCompany(@Valid @RequestBody Company company) {
        return companyService.saveOrUpdateCompany(company);
    }
}
