package com.prasys.framework.entity;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"", indexes = @Index(columnList = "id"))
@Transactional
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    public Long id;
    private Date createdOn;
    private String status;
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;
    public Long clientId;
    public Long memberId;
    public String userType;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private Integer phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ReportingTo")
    private User reportingTo;

    @OneToMany(mappedBy="reportingTo",cascade = CascadeType.ALL)
    private Set<User> subordinates ;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",joinColumns = {@JoinColumn(name="id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
    private  Set<Role> roles;
    private Long departmentId;


}
