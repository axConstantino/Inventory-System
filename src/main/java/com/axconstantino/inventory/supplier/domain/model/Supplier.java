package com.axconstantino.inventory.supplier.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class Supplier {
    private Long id;
    private String name;
    private String contactPerson;
    private String email;
    private String phone;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
