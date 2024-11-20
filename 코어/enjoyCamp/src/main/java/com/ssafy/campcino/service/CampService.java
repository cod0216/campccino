
package com.ssafy.campcino.service;

import com.ssafy.campcino.model.CampDto;
import java.util.List;

public interface CampService {
    List<CampDto> getAllCamps();
    List<CampDto> getSelectCamps(int region, List<Integer> category, String text);
    CampDto getCampById(int campId);
}
