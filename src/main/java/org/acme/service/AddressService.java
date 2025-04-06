package org.acme.service;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.mapper.AddressMapper;
import org.acme.model.Address;
import org.acme.model.Person;
import org.acme.repository.AddressRepository;
import org.acme.repository.PersonRepository;
import org.acme.rest.dto.AddressDTO;

import java.util.List;

@ApplicationScoped
public class AddressService {
    @Inject
    AddressRepository addressRepository;

    @Inject
    PersonRepository personRepository;

    public List<AddressDTO> listAll() {
        List<Address> addressList = addressRepository.listAll();
        return AddressMapper.INSTANCE.toDTO(addressList);
    }

    public AddressDTO findById(Long id) {
        Address address = addressRepository.findById(id);
        return AddressMapper.INSTANCE.toDTO(address);
    }

    @Transactional
    public AddressDTO persist(Long personId, AddressDTO addressDTO) {
        Address address = AddressMapper.INSTANCE.toEntity(addressDTO);
        Person person = personRepository.findById(personId);
        address.setPerson(person);
        addressRepository.persist(address);
        addressDTO.setId(address.getId());
        addressDTO.setPersonId(personId);
        return addressDTO;
    }

    //TODO -> EVALUATE field check
    @Transactional
    public AddressDTO update(Long id, AddressDTO addressDTO) {
        Address address = addressRepository.findById(id);
        if(address != null) {
            if(!StringUtil.isNullOrEmpty(addressDTO.getType())) {
                address.setType(addressDTO.getType());
            } else {
                address.setType(address.getType());
                addressDTO.setType(address.getType());
            }
            if(!StringUtil.isNullOrEmpty(addressDTO.getStreet())) {
                address.setStreet(addressDTO.getStreet());
            } else {
                address.setStreet(address.getStreet());
                addressDTO.setStreet(address.getStreet());
            }
            if(addressDTO.getStreetNumber() != null) {
                address.setStreetNumber(addressDTO.getStreetNumber());
            } else {
                address.setStreetNumber(address.getStreetNumber());
                addressDTO.setStreetNumber(address.getStreetNumber());
            }
            if(!StringUtil.isNullOrEmpty(addressDTO.getCity())) {
                address.setCity(addressDTO.getCity());
            } else {
                address.setCity(address.getCity());
                addressDTO.setCity(address.getCity());
            }
            addressRepository.persist(address);
            addressDTO.setId(address.getId());
            addressDTO.setPersonId(address.getPerson().getId());
            return addressDTO;
        }
        return null;
    }

    @Transactional
    public boolean delete(Long id) {
        Address address = addressRepository.findById(id);
        if (address != null) {
            addressRepository.delete(address);
            return true;
        }
        return false;
    }
}