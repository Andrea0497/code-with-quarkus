package org.acme.service;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.mapper.AddressMapper;
import org.acme.mapper.PersonMapper;
import org.acme.model.Person;
import org.acme.repository.PersonRepository;
import org.acme.rest.dto.PersonDTO;

import java.util.List;

@ApplicationScoped
public class PersonService {
    @Inject
    PersonRepository personRepository;

    public List<PersonDTO> listAll() {
        List<Person> personList = personRepository.listAll();
        return personList.stream().map(PersonMapper.INSTANCE::toDTOLight).toList();
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    @Transactional
    public PersonDTO persist(PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        personRepository.persist(person);
        personDTO.setId(person.getId());
        return personDTO;
    }

    //TODO -> EVALUATE field check
    @Transactional
    public PersonDTO update(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id);
        if(person != null) {
            if(!StringUtil.isNullOrEmpty(personDTO.getName())) {
                person.setName(personDTO.getName());
            } else {
                person.setName(person.getName());
                personDTO.setName(person.getName());
            }
            if(personDTO.getAge() != null) {
                person.setAge(personDTO.getAge());
            } else {
                person.setAge(person.getAge());
                personDTO.setAge(person.getAge());
            }
            if(personDTO.getAddressList() != null && !personDTO.getAddressList().isEmpty()) {
                person.setAddressList(AddressMapper.INSTANCE.toEntity(personDTO.getAddressList()));
            } else {
                person.setAddressList(person.getAddressList());
                personDTO.setAddressList(AddressMapper.INSTANCE.toDTO(person.getAddressList()));
            }
            personRepository.persist(person);
            personDTO.setId(person.getId());
            return personDTO;
        }
        return null;
    }

    @Transactional
    public boolean delete(Long id) {
        Person person = personRepository.findById(id);
        if (person != null) {
            personRepository.delete(person);
            return true;
        }
        return false;
    }
}