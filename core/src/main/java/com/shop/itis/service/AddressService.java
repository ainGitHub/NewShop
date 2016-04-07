package com.shop.itis.service;

import com.shop.itis.domain.Address;
import com.shop.itis.domain.UserInfo;
import com.shop.itis.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public List<Address> userAddress(UserInfo userInfo) {
        return addressRepository.userAddress(userInfo.getUsername());
    }
}
