
package com.campccino.service;

import java.util.List;

import com.campccino.mapper.SidoMapper;
import com.campccino.model.SidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SidoServiceImpl implements SidoService {

    @Autowired
    private SidoMapper sidoMapper;

    @Override
    public List<SidoEntity> getAllRegions() {
        return sidoMapper.findAll();
    }
}
