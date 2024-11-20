
package com.ssafy.campcino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.campcino.mapper.CampMapper;
import com.ssafy.campcino.model.CampDto;

@Service
public class CampServiceImpl implements CampService {

    @Autowired
    private CampMapper campMapper;

    @Override
    public List<CampDto> getAllCamps() {
        return campMapper.findAll();
    }

	@Override
	public List<CampDto> getSelectCamps(int region, List<Integer> category, String text) {
		return campMapper.getSelect(region, category, text);
	}
	
	@Override
    public CampDto getCampById(int campId) {
        return campMapper.getCampById(campId);
    }
}
