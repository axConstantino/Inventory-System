package com.axconstantino.inventory.supplier.infrastructure.dto;

import com.axconstantino.inventory.supplier.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SupplierDTO {
    private String name;
    private String contactPerson;
    private String email;
    private String phone;
    private Address address;
}
