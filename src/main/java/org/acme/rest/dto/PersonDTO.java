package org.acme.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//TODO -> LOMBOK ????
@Data
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private int age;
}