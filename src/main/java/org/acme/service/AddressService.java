package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.controller.api.AddressDTO;
import org.acme.mapper.AddressMapper;
import org.acme.model.Address;
import org.acme.repository.AddressRepository;

@ApplicationScoped
public class AddressService {
    @Inject
    AddressRepository addressRepository;
    @Inject
    AddressMapper addressMapper;
    @Transactional
    public void persist(AddressDTO addressDTO) {
        Address address = addressMapper.convertToModel(addressDTO);
        addressRepository.persist(address);
    }
    @Transactional
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}