package org.acme.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//TODO -> EVALUATE LOMBOK ????
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String street;
    private Long streetNumber;
    private String city;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}