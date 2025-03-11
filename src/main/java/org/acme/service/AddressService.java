package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.controller.api.AddressDTO;
import org.acme.mapper.AddressMapper;
import org.acme.repository.AddressRepository;

@ApplicationScoped
public class AddressService {
    @Inject
    AddressRepository addressRepository;
    @Inject
    AddressMapper addressMapper;
    @Transactional
    public void persist(AddressDTO addressDTO) {
        addressRepository.persist(addressMapper.convertToModel(addressDTO));
    }
    @Transactional
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}