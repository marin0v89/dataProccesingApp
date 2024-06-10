package com.home.dataprocessing;

import com.home.dataprocessing.model.CsvDataDTO;
import com.home.dataprocessing.service.CsvDataProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
