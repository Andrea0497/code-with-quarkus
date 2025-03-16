package org.acme.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.controller.api.PersonDTO;
import org.acme.model.Person;
import org.mapstruct.*;

import java.util.List;

@ApplicationScoped
@Mapper(componentModel = "cdi", uses = AddressMapper.class)
public interface PersonMapper {
    Person convertToModelFull(PersonDTO personDTO);
    @Mapping(source = "addresses", target = "addressesDTO")
    PersonDTO convertToDTOFull(Person person);
    @Named("Without.addressDTO")
    @Mapping(target = "addressesDTO", ignore = true)
    PersonDTO convertToDTO(Person person);
    @IterableMapping(qualifiedByName = "Without.addressDTO")
    List<PersonDTO> convertToDTO(List<Person> model);
}