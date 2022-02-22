package com.prasys.framework.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Table(name = "request_work_flow", indexes = @Index(columnList = "work_flow_id"))
public class RequestWorkFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "work_flow_id")
    public Long id;
    private String clientId;
    private Long requestId;
    private Long approverId;
    private String status;
    private Date modifiedOn;
    private Integer level;

}
