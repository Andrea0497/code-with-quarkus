package org.acme.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.controller.api.AddressDTO;
import org.acme.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@ApplicationScoped
@Mapper(componentModel = "cdi")
public interface AddressMapper {
    @Mapping(source = "personId", target = "person.id")
    Address convertToModel(AddressDTO addressDTO);
    @Mapping(source = "person.id", target = "personId")
    AddressDTO convertToDTO(Address address);
}