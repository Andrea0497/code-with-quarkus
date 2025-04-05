package org.acme.service;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
        List<Person> people = personRepository.listAll();
        return PersonMapper.INSTANCE.toDTO(people);
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

    //TODO -> EVALUATE PATH-PRAM "id"
    @Transactional
    public PersonDTO update(Long id, PersonDTO personDTO) {
        Person existingPerson = personRepository.findById(id);
        if(existingPerson != null) {
            if(!StringUtil.isNullOrEmpty(personDTO.getName())) {
                existingPerson.setName(personDTO.getName());
            } else {
                existingPerson.setName(existingPerson.getName());
                personDTO.setName(existingPerson.getName());
            }
            if(personDTO.getAge() != 0) {
                existingPerson.setAge(personDTO.getAge());
            } else {
                existingPerson.setAge(existingPerson.getAge());
                personDTO.setAge(existingPerson.getAge());
            }
            personRepository.persist(existingPerson);
            personDTO.setId(existingPerson.getId());
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