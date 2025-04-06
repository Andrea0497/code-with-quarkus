package org.acme.mapper;

import org.acme.model.Person;
import org.acme.rest.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toEntity(PersonDTO personDTO);
    PersonDTO toDTO(Person person);

    @Mapping(target = "addressList", ignore = true)
    PersonDTO toDTOLight(Person person);
}