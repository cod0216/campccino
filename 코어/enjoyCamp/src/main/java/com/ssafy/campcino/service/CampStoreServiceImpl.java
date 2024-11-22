package com.ssafy.campcino.service;

import com.ssafy.campcino.dto.responseDto.CampProductDto;
import com.ssafy.campcino.mapper.CampStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampStoreServiceImpl implements CampStoreService {

    @Autowired
    private CampStoreMapper campStoreMapper;

    @Override
    public List<CampProductDto> getAllCampProducts() {
        List<CampProductDto> products = campStoreMapper.findAllCampProducts();
        System.out.println("Fetched Products: " + products);
        return products;
    }

    @Override
    public CampProductDto getCampProductById(int productId) {
        CampProductDto product = campStoreMapper.findCampProductById(productId);
        System.out.println("Fetched Product by ID " + productId + ": " + product);
        return product;
    }

    @Override
    public List<CampProductDto> getRandomProducts() {
        List<CampProductDto> randomProducts = campStoreMapper.findRandomProducts();
        System.out.println("Fetched Random Products: " + randomProducts);
        return randomProducts;
    }
}
