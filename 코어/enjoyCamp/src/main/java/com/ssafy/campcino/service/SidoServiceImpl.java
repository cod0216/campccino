
package com.ssafy.campcino.service;

import com.ssafy.campcino.model.SidoDto;
import com.ssafy.campcino.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SidoServiceImpl implements SidoService {

    @Autowired
    private SidoRepository sidoRepository;

    @Override
    public List<SidoDto> getAllRegions() {
        return sidoRepository.findAll();
    }
}
