package com.prasys.framework.repo;

import com.prasys.framework.entity.PurchaseRequisition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<PurchaseRequisition,Long> {
}
