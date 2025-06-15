
package com.campccino.mapper;

import com.campccino.model.AddressEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AddressMapper {
    AddressEntity findByCampId(@Param("campId") int campId);
}
