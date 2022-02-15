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
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String address;
    private String email;
    private Integer phone;
    private String panNumber;
    private String gstNumber;
    private String companyType;
    private String status;
    private Date modifiedOn;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_vendors",
            joinColumns = { @JoinColumn(name = "client_id", referencedColumnName = "client_id"),
                            @JoinColumn(name = "date",referencedColumnName = "modifiedOn"),
                            @JoinColumn(name = "status",referencedColumnName = "status"), },
            inverseJoinColumns = { @JoinColumn(name = "vendor_id", referencedColumnName = "client_id") } )
    private List<Company> vendors;

}
