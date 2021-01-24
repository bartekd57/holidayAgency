package com.travel_agency.dto;

import com.travel_agency.model.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BranchDTO {

    private Long id;
    private Address address;
}
