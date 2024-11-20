
package com.ssafy.campcino.service;

import com.ssafy.campcino.model.AddressDto;
import com.ssafy.campcino.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressDto getAddressByCampId(int campId) {
        return addressRepository.findByCampId(campId);
    }
}
