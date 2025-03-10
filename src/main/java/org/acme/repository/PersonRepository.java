package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityGraph;
import org.acme.model.Person;

import java.util.Optional;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    public Optional<Person> findByIdWithAddresses(Long id) {
        EntityGraph<?> entityGraph = getEntityManager().getEntityGraph("Person.withAddresses");
        return find("id", id)
                .withHint("jakarta.persistence.loadgraph", entityGraph)
                .firstResultOptional();
        //Se non si vuole definire l'Entity Graph a livello di entità, lo si può creare dinamicamente:
        /*EntityGraph<Person> personEntityGraph = getEntityManager().createEntityGraph(Person.class);
        personEntityGraph.addAttributeNodes("addresses");
        return find("id", id)
                .withHint("jakarta.persistence.loadgraph", personEntityGraph)
                .firstResultOptional();*/
    }
}