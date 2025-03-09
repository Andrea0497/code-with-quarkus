package org.acme.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.config.QuarkusMappingConfig;
import org.acme.controller.api.AddressDTO;
import org.acme.model.Address;
import org.mapstruct.Mapper;

//TODO - CHECK THE COMMENTED METHOD AND RESOLVE ANY MAPPER'S ISSUES DURING BEAN CREATION
@ApplicationScoped
@Mapper(config = QuarkusMappingConfig.class)
public interface AddressMapper {
    Address convertToModel(AddressDTO addressDTO);
}