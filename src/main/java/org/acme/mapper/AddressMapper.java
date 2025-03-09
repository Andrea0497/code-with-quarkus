package org.acme.mapper;

import org.acme.controller.api.AddressDTO;
import org.acme.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//TODO - CHECK THE COMMENTED METHOD AND RESOLVE ANY MAPPER'S ISSUES DURING BEAN CREATION
@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    //List<Address> convertToModel(List<AddressDTO> addressDTOS);
    Address convertToModel(AddressDTO addressDTO);
    //AddressDTO convertToDTO(Address model);
}