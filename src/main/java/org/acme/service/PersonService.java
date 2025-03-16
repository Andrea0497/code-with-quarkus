package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.controller.api.PersonDTO;
import org.acme.mapper.PersonMapper;
import org.acme.model.Person;
import org.acme.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {
    @Inject
    PersonRepository personRepository;
    @Inject
    PersonMapper personMapper;
    public List<PersonDTO> findByString(String string) {
        String lowerCaseString = string.toLowerCase();
        List<Person> listPeople = personRepository.findAll().stream().toList();
        List<Person> listPeopleFound = new ArrayList<>();
        for(Person person : listPeople){
            if((person.getName() != null && person.getName().toLowerCase().startsWith(lowerCaseString)) ||
                    (person.getLastName() != null && person.getLastName().toLowerCase().startsWith(lowerCaseString)) ||
                    (person.getLegalName() != null && person.getLegalName().toLowerCase().startsWith(lowerCaseString))) {
                listPeopleFound.add(person);
            }
        }
        return personMapper.convertToDTO(listPeopleFound);
    }
    public PersonDTO findById(Long id) {
        Person person = personRepository.findByIdWithAddresses(id).orElseThrow();
        return personMapper.convertToDTOFull(person);
    }
    @Transactional
    public void persist(PersonDTO personDTO){
        Person person = personMapper.convertToModelFull(personDTO);
        personRepository.persist(person);
    }
    @Transactional
    public void deleteById(Long id){
        personRepository.deleteById(id);
    }
}