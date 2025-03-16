package org.acme.controller.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String type;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;
    private Long personId;
}