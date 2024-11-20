
package com.ssafy.enjoyCamp;

import com.ssafy.campcino.model.CampDto;
import com.ssafy.campcino.service.CampService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CampControllerTest {

//    @Mock
//    private CampService campService;
//
//    @InjectMocks
//    private com.ssafy.campcino.controller.CampController campController;
//
//    public CampControllerTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllCamps() {
//        // Arrange
//        CampDto camp1 = new CampDto();
//        camp1.setCampId(1);
//        camp1.setCampName("Test Camp 1");
//
//        CampDto camp2 = new CampDto();
//        camp2.setCampId(2);
//        camp2.setCampName("Test Camp 2");
//
//        List<CampDto> mockCamps = Arrays.asList(camp1, camp2);
//        when(campService.getAllCamps()).thenReturn(mockCamps);
//
//        // Act
//        List<CampDto> result = campController.getAllCamps();
//
//        // Assert
//        assertEquals(2, result.size());
//        assertEquals("Test Camp 1", result.get(0).getCampName());
//        assertEquals("Test Camp 2", result.get(1).getCampName());
//    }
}
