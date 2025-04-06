package org.acme.mapper;

import org.acme.model.Address;
import org.acme.rest.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toEntity(AddressDTO addressDTO);
    AddressDTO toDTO(Address address);
    List<Address> toEntity(List<AddressDTO> addressDTOList);
    List<AddressDTO> toDTO(List<Address> addressList);
}