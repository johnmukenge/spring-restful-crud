package com.nttdata.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Company {

    @Id
    @Column(name = "vat_number", length = 11)
    @Size(min = 11, max = 11, message = "Vat number must be 11 characters")
    private String vatnumber;
    @Column(name = "business_name", length = 50)
    private String businessname;
    @Column(name = "address_location", length = 100)
    private String addressLocation;
    @Column(name = "employees_number")
    private Integer employeesnumber;


}
