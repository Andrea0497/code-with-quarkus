package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.controller.api.PersonDTO;
import org.acme.mapper.PersonMapper;
import org.acme.model.Person;
import org.acme.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonService {
    @Inject
    PersonRepository personRepository;
    @Inject
    PersonMapper personMapper;
    public List<PersonDTO> findByString(String string) {
        String lowerCaseString = string.toLowerCase();
        List<Person> listPeople = (List<Person>) personRepository.findAll();
        List<Person> listPeopleFound = new ArrayList<>();
        for(Person person:listPeople){
            if((person.getName().toLowerCase().startsWith(lowerCaseString)) ||
                    (person.getLastName().toLowerCase().startsWith(lowerCaseString)) ||
                    (person.getLegalName().toLowerCase().startsWith(lowerCaseString))) {
                listPeopleFound.add(person);
            }
        }
        return personMapper.convertToDTOLight(listPeopleFound);
    }
    //TODO - GESTIRE .orElseThrow()
    public PersonDTO findById(Long id) {
        return personMapper.convertToDTOFull(personRepository.findByIdWithAddresses(id).orElseThrow());
    }
    public void persist(PersonDTO personDTO){
        if(personDTO.getLegalName()!=null){
            personDTO.setLegalPerson(true);
        }
        personRepository.persist(personMapper.convertToModelFull(personDTO));
    }
    public void deleteById(Long id){
        personRepository.deleteById(id);
    }
}