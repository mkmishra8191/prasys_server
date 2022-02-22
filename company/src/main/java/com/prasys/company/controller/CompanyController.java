package com.prasys.company.controller;


import com.prasys.auth.service.MyUserDetailsService;
import com.prasys.company.service.CompanyService;
import com.prasys.framework.dto.UserMap;
import com.prasys.framework.dto.UserRole;
import com.prasys.framework.entity.Company;
import com.prasys.framework.entity.Item;
import com.prasys.framework.entity.Role;
import com.prasys.framework.entity.User;
import com.prasys.framework.repo.CompanyRepo;
import com.prasys.framework.repo.RequestRepo;
import com.prasys.framework.repo.RoleRepo;
import com.prasys.framework.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("v1")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private RoleRepo roleRepo;



    @PostMapping("/admin/addvendor")
    public ResponseEntity<Company> addVendor(@RequestBody Company company) {
             company.setCompanyType("vendor");
           return new ResponseEntity<Company>(companyRepo.save(company),HttpStatus.CREATED);
    }
    @PostMapping("/admin/mapvendor")
    public ResponseEntity<Company> mapVendor(@RequestBody Company company) {
        Company client = companyRepo.getOne(MyUserDetailsService.user.getId());
             client.getVendors().add(company);
        return new ResponseEntity<Company>(companyRepo.save(client),HttpStatus.CREATED);
    }
    @PostMapping("/admin/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.setClientId(MyUserDetailsService.user.clientId);
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userRepo.getOne(id), HttpStatus.OK);
    }
    @PostMapping("/admin/assignrole")
    public ResponseEntity<User> addRole(@RequestBody UserRole userRole) {
        Role role = roleRepo.getOne(userRole.getRoleId());
        User user = userRepo.getOne(userRole.getUserId());
        user.getRoles().add(role);
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
    }
    @PostMapping("/admin/mapuser")
    public ResponseEntity<User> mapUser(@RequestBody UserMap userMap) {
        User user = userRepo.getOne(userMap.getUserId());
        User manager = userRepo.getOne(userMap.getManagerId());

        user.setReportingTo(manager);
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
    }
    @GetMapping("/admin/company/{id}")
    public ResponseEntity<Company> getClient(@PathVariable("id") Long id) {
        return new ResponseEntity<>(companyRepo.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleRepo.findAll(), HttpStatus.OK);
    }


}
