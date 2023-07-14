package com.nttdata.demo.service;

import com.nttdata.demo.entity.Company;
import com.nttdata.demo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    Company saveOrUpdateCompany(Company company);
    Map<Boolean, String> deleteCompany(String id);

    Company getCompany(String id);

    List<Company> getAllCompanies();
}
