package com.prasys.framework.repo;

import com.prasys.framework.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<PurchaseOrder,Long> {
}
