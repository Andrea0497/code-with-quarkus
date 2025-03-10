package org.acme.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.controller.api.PersonDTO;
import org.acme.model.Person;
import org.mapstruct.*;

import java.util.List;

@ApplicationScoped
//@Mapper(componentModel = "cdi", uses = {AddressMapper.class})
@Mapper(componentModel = "cdi")
public interface PersonMapper {
    Person convertToModelFull(PersonDTO personDTO);
    PersonDTO convertToDTOFull(Person person);
    @Named("light")
    @Mappings({@Mapping(target = "addresses", ignore = true)})
    PersonDTO convertToDTOLight(Person person);
    @IterableMapping(qualifiedByName = "light")
    List<PersonDTO> convertToDTOLight(List<Person> model);
}