package com.nttdata.demo.repository;

import com.nttdata.demo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {

    long countBy();
}
