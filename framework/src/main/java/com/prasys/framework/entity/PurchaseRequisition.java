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
@Table(name = "purchase_requisition", indexes = @Index(columnList = "pr_id"))
public class PurchaseRequisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "pr_id")
    public Long id;
    private Date createdOn;
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;
    public String clientId;
    public Long memberId;
    public Long rTypeId;
    @ElementCollection
    @CollectionTable(name = "pr_items", joinColumns = @JoinColumn(name = "pr_id"), foreignKey = @ForeignKey(name = "pr_items_prs_fk"))
    private List<Item> items;
    @Column(columnDefinition = "varchar(255) default 'Initiated'")
    public  String  approvalStatus;

}
