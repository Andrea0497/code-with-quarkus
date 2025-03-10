package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO - TO CHECK -> LOMBOK ANNOTATION
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address extends PanacheEntity {
    private String type;
    private String street;
    private String streetNumber;
    private int postalCode;
    private String city;
    private Long personId;
}