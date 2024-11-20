
package com.ssafy.campcino.mapper;

import com.ssafy.campcino.model.AddressDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AddressMapper {
    AddressDto findByCampId(@Param("campId") int campId);
}
