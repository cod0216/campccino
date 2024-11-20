
package com.ssafy.campcino.controller;

import com.ssafy.campcino.dto.responseDto.CampDto;
import com.ssafy.campcino.model.*;
import com.ssafy.campcino.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CampController {

    @Autowired
    private CampService campService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SidoService sidoService;

    // Camp endpoints
//    @GetMapping("/camps")
//    public List<CampDto> getAllCamps() {
//        return campService.getAllCamps();
//    }

    @GetMapping("/camps")
    public List<CampDto> getSelectCamps(
        @RequestParam(required = false, defaultValue = "0") int region,
        @RequestParam(required = false) List<Integer> category,
        @RequestParam(required = false, defaultValue = "") String text) {
        if (category == null || category.isEmpty()) {
            category = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        }
        return campService.getSelectCamps(region, category, text);
    }
    
    @GetMapping("/camps/{id}")
    public CampDto getCampById(@PathVariable("id") int campId) {
        return campService.getCampById(campId);
    }

    // Address endpoints
    @GetMapping("/camps/{id}/address")
    public AddressEntity getAddressByCampId(@PathVariable int id) {
        return addressService.getAddressByCampId(id);
    }

    // Category endpoints
    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Region endpoints
    @GetMapping("/regions")
    public List<SidoEntity> getAllRegions() {
        return sidoService.getAllRegions();
    }
}
