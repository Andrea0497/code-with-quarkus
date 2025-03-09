package org.acme.controller.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO - TO CHECK -> LOMBOK ANNOTATION
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String type;
    private String street;
    private String streetNumber;
    private int postalCode;
    private String city;
    private Long personId;
}