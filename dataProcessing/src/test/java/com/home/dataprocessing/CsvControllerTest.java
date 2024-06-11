package com.home.dataprocessing;

import com.home.dataprocessing.controller.CsvController;
import com.home.dataprocessing.model.CsvDataDTO;
import com.home.dataprocessing.service.CsvDataProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CsvController.class)
public class CsvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CsvDataProcessService csvDataProcessService;

    @InjectMocks
    private CsvController csvController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(csvController).build();
    }

    @Test
    public void testGetTotalCost() throws Exception {
        when(csvDataProcessService.getAllRecords()).thenReturn(Arrays.asList(new CsvDataDTO()));

        mockMvc.perform(get("/total-cost")
                        .param("startTime", "2024-04-23 00:00:00 UTC")
                        .param("endTime", "2024-04-23 01:00:00 UTC")
                        .param("location", "US")
                        .param("sku", "5490-F7B7-8DF6"))
                .andExpect(status().isOk());

        verify(csvDataProcessService, times(1)).getAllRecords();
    }

    @Test
    public void getCostPerGroup() throws Exception {
        when(csvDataProcessService.getAllRecords()).thenReturn(Arrays.asList(new CsvDataDTO()));

        mockMvc.perform(get("/grouped-cost")
                        .param("groupBy", "date")
                        .param("groupBy", "country"))
                .andExpect(status().isOk());

        verify(csvDataProcessService, times(1)).getAllRecords();
    }

    @Test
    public void  searchByLabelAndCountry()throws Exception{
        when(csvDataProcessService.getAllRecords()).thenReturn(Arrays.asList(new CsvDataDTO()));

        mockMvc.perform(get("/search")
                .param("key","environment")
                .param("value","development")
                .param("page","1")
                .param("size","10"))
                .andExpect(status().isOk());

        verify(csvDataProcessService, times(1)).getAllRecords();
    }
}
