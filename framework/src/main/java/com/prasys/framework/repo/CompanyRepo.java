package com.prasys.framework.repo;


import com.prasys.framework.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company,Long> {
   }
