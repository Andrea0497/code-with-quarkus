package org.acme.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//TODO -> EVALUATE LOMBOK ????
@Data
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String type;
    private String street;
    private Long streetNumber;
    private String city;
    private Long personId;
}