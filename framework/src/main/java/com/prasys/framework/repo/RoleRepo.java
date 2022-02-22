package com.prasys.framework.repo;


import com.prasys.framework.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    }
