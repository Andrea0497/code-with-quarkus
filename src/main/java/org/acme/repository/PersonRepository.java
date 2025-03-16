package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Person;

import java.util.Optional;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    public Optional<Person> findByIdWithAddresses(Long id) {
        return find("FROM Person p LEFT JOIN FETCH p.addresses WHERE p.id = ?1", id).firstResultOptional();
    }
}