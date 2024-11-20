
package com.ssafy.campcino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.campcino.mapper.SidoMapper;
import com.ssafy.campcino.model.SidoDto;

@Service
public class SidoServiceImpl implements SidoService {

    @Autowired
    private SidoMapper sidoMapper;

    @Override
    public List<SidoDto> getAllRegions() {
        return sidoMapper.findAll();
    }
}
