package com.nttdata.demo.service;

import com.nttdata.demo.entity.Company;
import com.nttdata.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company saveOrUpdateCompany(Company company) {
        Company savedCompany = null;
        try {
            savedCompany = companyRepository.save(company);
        }catch (IllegalArgumentException | OptimisticLockingFailureException e ){
            e.printStackTrace();
        }
        return savedCompany;
    }

    @Override
    public Map<Boolean, String> deleteCompany(String vatNumber) {
        Map<Boolean, String> deletionStatus = new HashMap<>();
        if(vatNumber == null || vatNumber.isEmpty()){
            deletionStatus.put(false, "Vat Number is empty");
            return deletionStatus;
        }
        if(!companyRepository.existsById(vatNumber)){
            deletionStatus.put(false, "Company with vat number " + vatNumber + " does not exist");
            return deletionStatus;
        }
        companyRepository.deleteById(vatNumber);
        deletionStatus.put(false, "Delete company success");
        return deletionStatus;
    }

    @Override
    public Company getCompany(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
