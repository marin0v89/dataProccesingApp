package com.home.dataprocessing;

import com.home.dataprocessing.model.CsvDataDTO;
import com.home.dataprocessing.model.Pagination;
import com.home.dataprocessing.service.CsvDataProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvDataProcessServiceTest {

    @InjectMocks
    private CsvDataProcessService csvDataProcessService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateTotalCost() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setUsageStartTime("2024-04-23 00:00:00 UTC");
        firstRecord.setUsageEndTime("2024-04-23 01:00:00 UTC");
        firstRecord.setLocationLocation("US");
        firstRecord.setSkuId("5490-F7B7-8DF6");
        firstRecord.setCost(10.0);

        CsvDataDTO secondRecord = new CsvDataDTO();
        secondRecord.setUsageStartTime("2024-04-23 00:00:00 UTC");
        secondRecord.setUsageEndTime("2024-04-23 01:00:00 UTC");
        secondRecord.setLocationLocation("US");
        secondRecord.setSkuId("5490-F7B7-8DF6");
        secondRecord.setCost(20.0);

        List<CsvDataDTO> records = Arrays.asList(firstRecord, secondRecord);
        double totalCost = csvDataProcessService.calculateTotalCost(records);
        assertEquals(30.0, totalCost);
    }

    @Test
    public void testFilterRecordsByTime() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setUsageStartTime("2024-04-23 00:00:00 UTC");
        firstRecord.setUsageEndTime("2024-04-23 01:00:00 UTC");

        CsvDataDTO secondRecord = new CsvDataDTO();
        secondRecord.setUsageStartTime("2024-04-23 02:00:00 UTC");
        secondRecord.setUsageEndTime("2024-04-23 03:00:00 UTC");

        List<CsvDataDTO> records = Arrays.asList(firstRecord, secondRecord);
        List<CsvDataDTO> filteredRecords = csvDataProcessService.filterRecordsByTime(records, "2024-04-23 00:00:00 UTC", "2024-04-23 01:00:00 UTC");
        assertEquals(1, filteredRecords.size());
    }

    @Test
    public void testFilterRecordsByLocation() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setLocationLocation("US");

        CsvDataDTO secondRecord = new CsvDataDTO();
        secondRecord.setLocationLocation("BG");

        List<CsvDataDTO> records = Arrays.asList(firstRecord, secondRecord);
        List<CsvDataDTO> filteredRecords = csvDataProcessService.filterRecordsByLocation(records, "US");
        assertEquals(1, filteredRecords.size());
    }

    @Test
    public void testFilterRecordsBySku() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setSkuId("5490-F7B7-8DF6");

        CsvDataDTO secondRecord = new CsvDataDTO();
        secondRecord.setSkuId("1234-ABCD-5678");

        List<CsvDataDTO> records = Arrays.asList(firstRecord, secondRecord);
        List<CsvDataDTO> filteredRecords = csvDataProcessService.filterRecordsBySku(records, "5490-F7B7-8DF6");
        assertEquals(1, filteredRecords.size());
    }

    @Test
    public void testCalculateCostPerGroup() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setUsageStartTime("2024-04-23 00:00:00 UTC");
        firstRecord.setUsageEndTime("2024-04-23 01:00:00 UTC");
        firstRecord.setLocationLocation("US");
        firstRecord.setSkuId("5490-F7B7-8DF6");
        firstRecord.setCost(10.0);
        firstRecord.setLocationCountry("USA");
        firstRecord.setServiceId("service1");

        CsvDataDTO secondRecord = new CsvDataDTO();
        secondRecord.setUsageStartTime("2024-04-23 02:00:00 UTC");
        secondRecord.setUsageEndTime("2024-04-23 03:00:00 UTC");
        secondRecord.setLocationLocation("US");
        secondRecord.setSkuId("5490-F7B7-8DF6");
        secondRecord.setCost(20.0);
        secondRecord.setLocationCountry("USA");
        secondRecord.setServiceId("service1");

        List<CsvDataDTO> records = Arrays.asList(firstRecord, secondRecord);
        List<String> groupBy = Arrays.asList("country", "service");

        List<Map<String, Object>> result = csvDataProcessService.calculateCostPerGroup(records, groupBy);

        assertEquals(1, result.size());
        assertEquals(30.0, result.get(0).get("cost"));
        assertEquals("USA", result.get(0).get("country"));
        assertEquals("service1", result.get(0).get("service"));
    }

    @Test
    public void testSearchByLabelAndCountry() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setLabels(new HashMap<>(Map.of("labelKey", "labelValue")));
        firstRecord.setLocationCountry("USA");

        CsvDataDTO secondRecord = new CsvDataDTO();
        secondRecord.setLabels(new HashMap<>(Map.of("labelKey", "labelValue")));
        secondRecord.setLocationCountry("CAN");

        List<CsvDataDTO> records = Arrays.asList(firstRecord, secondRecord);
        Pagination<CsvDataDTO> result = csvDataProcessService.searchByLabelAndCountry(records, "labelKey", "labelValue", "USA", 1, 1);

        assertEquals(1, result.getItems().size());
        assertEquals(1, result.getPage());
        assertEquals(1, result.getSize());
        assertEquals(1, result.getTotalItems());
    }

    @Test
    public void testSearchByLabelAndCountryInvalidPageSize() {
        CsvDataDTO firstRecord = new CsvDataDTO();
        firstRecord.setLabels(new HashMap<>(Map.of("labelKey", "labelValue")));
        firstRecord.setLocationCountry("USA");

        List<CsvDataDTO> records = Arrays.asList(firstRecord);

        assertThrows(IllegalArgumentException.class, () -> {
            csvDataProcessService.searchByLabelAndCountry(records, "labelKey", "labelValue", "USA", 0, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            csvDataProcessService.searchByLabelAndCountry(records, "labelKey", "labelValue", "USA", 1, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            csvDataProcessService.searchByLabelAndCountry(records, "labelKey", "labelValue", "USA", 1, 21);
        });
    }
}
