
package com.ssafy.campcino.service;

import java.util.List;

import com.ssafy.campcino.dto.responseDto.CampDto;

public interface CampService {
    List<CampDto> getAllCamps();
    List<CampDto> getSelectCamps(int region, List<Integer> category, String text);
    CampDto getCampById(int campId);
}
