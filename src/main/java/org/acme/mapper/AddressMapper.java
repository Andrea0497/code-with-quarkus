package org.acme.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.controller.api.AddressDTO;
import org.acme.model.Address;
import org.mapstruct.Mapper;

@ApplicationScoped
@Mapper(componentModel = "cdi")
public interface AddressMapper {
    Address convertToModel(AddressDTO addressDTO);
}