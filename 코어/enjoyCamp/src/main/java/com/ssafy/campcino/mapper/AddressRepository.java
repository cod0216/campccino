
package com.ssafy.campcino.repository;

import com.ssafy.campcino.model.AddressDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AddressRepository {
    AddressDto findByCampId(@Param("campId") int campId);
}
