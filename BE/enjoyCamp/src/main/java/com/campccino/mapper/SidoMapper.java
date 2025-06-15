
package com.campccino.mapper;

import com.campccino.model.SidoEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface SidoMapper {
    List<SidoEntity> findAll();
}
