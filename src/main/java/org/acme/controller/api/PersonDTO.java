package org.acme.controller.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String lastName;
    private String fiscalCode;
    private String legalName;
    private String vatNumber;
    private boolean legalPerson;
    private List<AddressDTO> addressesDTO;
}