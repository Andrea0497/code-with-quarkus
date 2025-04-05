package org.acme.mapper;

import org.acme.model.Person;
import org.acme.rest.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(Person person);
    List<PersonDTO> toDTO(List<Person> people);
    Person toEntity(PersonDTO personDTO);
}