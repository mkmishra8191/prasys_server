package com.prasys.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.*;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties("hibernateLazyInitializer")
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
@Transactional
@Table(name = "company")
public class Company implements Serializable{
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


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_vendors",
            joinColumns = { @JoinColumn(name = "client_id", referencedColumnName = "client_id")},
            inverseJoinColumns = { @JoinColumn(name = "vendor_id", referencedColumnName = "client_id") } )
    private List<Company> vendors;

}
