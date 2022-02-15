package com.prasys.framework.repo;


import com.prasys.framework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email = :userName")
    User findUserByEmail(@Param("userName") String userName);}
