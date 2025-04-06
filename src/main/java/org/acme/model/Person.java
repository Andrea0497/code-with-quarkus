package org.acme.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
//TODO -> EVALUATE LOMBOK ????
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    //TODO -> EVALUATE CASCADE
    @OneToMany(mappedBy = "person")
    private List<Address> addressList;
}