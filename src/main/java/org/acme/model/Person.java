package org.acme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//TODO - CHECK -> ANNOTAZIONI LOMBOK(righe 12->15)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraph(name = "Person.withAddresses", attributeNodes = {@NamedAttributeNode("addresses")})
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String fiscalCode;
    private String legalName;
    private String vatNumber;
    private boolean legalPerson;
    //TODO - CHECK -> CONDIZIONI ONETOMANY
    @OneToMany(mappedBy = "personId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addresses;
}