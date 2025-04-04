package com.axconstantino.inventory.supplier.infrastructure.dto;

import com.axconstantino.inventory.supplier.domain.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SupplierDTO {
    @NotBlank(message = "Supplier name is required")
    @Size(max = 100, message = "Supplier name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Contact person is required")
    @Size(max = 100, message = "Contact person name must not exceed 100 characters")
    private String contactPerson;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[1-9][0-9]{9}$", message = "Phone number must be 10 digits")
    private String phone;

    private Address address;
}
