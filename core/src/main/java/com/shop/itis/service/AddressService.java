package com.shop.itis.service;

import com.shop.itis.domain.Address;
import com.shop.itis.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public void add(Address address) {
        addressRepository.add(address);
    }

    @Transactional
    public void update(Address address) {
        addressRepository.update(address);
    }
}
