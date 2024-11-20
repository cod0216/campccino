
package com.ssafy.campcino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.campcino.mapper.AddressMapper;
import com.ssafy.campcino.model.AddressDto;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressRepository;

    @Override
    public AddressDto getAddressByCampId(int campId) {
        return addressRepository.findByCampId(campId);
    }
}
