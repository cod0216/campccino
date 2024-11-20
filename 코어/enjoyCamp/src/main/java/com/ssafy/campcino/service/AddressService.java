
package com.ssafy.campcino.service;

import com.ssafy.campcino.model.AddressDto;

public interface AddressService {
    AddressDto getAddressByCampId(int campId);
}
