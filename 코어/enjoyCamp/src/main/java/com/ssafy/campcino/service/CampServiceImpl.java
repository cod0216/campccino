
package com.ssafy.campcino.service;

import com.ssafy.campcino.model.CampDto;
import com.ssafy.campcino.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampServiceImpl implements CampService {

    @Autowired
    private CampRepository campRepository;

    @Override
    public List<CampDto> getAllCamps() {
        return campRepository.findAll();
    }

	@Override
	public List<CampDto> getSelectCamps(int region, List<Integer> category, String text) {
		return campRepository.getSelect(region, category, text);
	}
	
	@Override
    public CampDto getCampById(int campId) {
        return campRepository.getCampById(campId);
    }
}
