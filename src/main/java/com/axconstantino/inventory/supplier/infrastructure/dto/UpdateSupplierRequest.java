package com.axconstantino.inventory.supplier.infrastructure.dto;

import com.axconstantino.inventory.supplier.domain.model.Address;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateSupplierRequest {
    @Size(max = 100, message = "Supplier name must not exceed 100 characters")
    private String name;

    @Size(max = 100, message = "Contact person name must not exceed 100 characters")
    private String contactPerson;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[1-9][0-9]{9}$", message = "Phone number must be 10 digits")
    private String phone;

    private Address address;
}
