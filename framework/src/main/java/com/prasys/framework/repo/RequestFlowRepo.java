package com.prasys.framework.repo;


import com.prasys.framework.entity.RequestWorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestFlowRepo extends JpaRepository<RequestWorkFlow,Long> {


   @Query("select u from RequestWorkFlow u where u.requestId = :id AND u.status = :status order by u.level asc")
   List<RequestWorkFlow> findAllWorkFlow(Long id,String status);

   }
