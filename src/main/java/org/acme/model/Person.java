package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//TODO - TO CHECK -> LOMBOK ANNOTATION
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//TODO - TO CHECK -> ENTITYGRAPH
@NamedEntityGraph(name = "personFull", attributeNodes = {@NamedAttributeNode("addresses")})
@Entity
@Table(name = "people")
public class Person extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String fiscalCode;
    private String legalName;
    private String vatNumber;
    private boolean legalPerson;
    //TODO - TO CHECK -> ONETOMANY CONDITIONS
    @OneToMany(mappedBy = "personId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addresses;
}