package com.prasys.framework.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.util.Date;

@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String client_id;
    private Date date;
    private  String status;
    private Long vendor_id;
}
