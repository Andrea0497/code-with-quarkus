package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Address;

//TODO OK -> TO CHECK CLASS/INTERFACE
@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {}