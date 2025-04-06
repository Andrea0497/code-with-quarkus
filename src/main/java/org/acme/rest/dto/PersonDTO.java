package org.acme.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//TODO -> EVALUATE LOMBOK ????
@Data
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private Integer age;
    private List<AddressDTO> addressList;
}