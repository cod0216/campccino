
package com.campccino.service;

import com.campccino.mapper.AddressMapper;
import com.campccino.model.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressRepository;

    @Override
    public AddressEntity getAddressByCampId(int campId) {
        return addressRepository.findByCampId(campId);
    }
}
