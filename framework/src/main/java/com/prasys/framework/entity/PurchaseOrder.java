package com.prasys.framework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Table(name = "purchase_order",indexes = @Index(columnList = "po_id"))
public class PurchaseOrder {
    @Id
    @Column(name = "po_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendorId;
    private String clientId;
    private Date createdOn;
    private Date modifiedOn;
    public Long memberId;
    private Long createdBy;
    private Long modifiedBy;
    @Embedded
    @JoinColumn(name = "PurchaseOrder_ID")
    private List<Item> items;
    @Column(columnDefinition = "varchar(255) default 'New'")
    private String status;


}
