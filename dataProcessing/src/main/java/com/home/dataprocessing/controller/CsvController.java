package com.home.dataprocessing.controller;

import com.home.dataprocessing.model.CsvDataDTO;
import com.home.dataprocessing.model.Pagination;
import com.home.dataprocessing.service.CsvDataProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class CsvController {
    @Autowired
    private CsvDataProcessService csvService;

    @GetMapping("/total-cost")
    public double getTotalCost(@RequestParam(required = false) String startTime,
                               @RequestParam(required = false) String endTime,
                               @RequestParam(required = false) String location,
                               @RequestParam(required = false) String sku) throws IOException {
        List<CsvDataDTO> records = csvService.getAllRecords();

        if (startTime != null || endTime != null) {
            records = csvService.filterRecordsByTime(records, startTime, endTime);
        }
        if (location != null) {
            records = csvService.filterRecordsByLocation(records, location);
        }
        if (sku != null) {
            records = csvService.filterRecordsBySku(records, sku);
        }

        return csvService.calculateTotalCost(records);
    }

    @GetMapping("/grouped-cost")
    public List<Map<String, Object>> getCostPerGroup(@RequestParam(required = false) List<String> groupBy) throws IOException {
        List<CsvDataDTO> records = csvService.getAllRecords();
        return csvService.calculateCostPerGroup(records, groupBy);
    }

    @GetMapping("/search")
    public Pagination<CsvDataDTO> searchByLabelAndCountry(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam(required = false) String country,
            @RequestParam int page,
            @RequestParam int size
    ) throws IOException {
        List<CsvDataDTO> records = csvService.getAllRecords();
        return csvService.searchByLabelAndCountry(records, key, value, country, page, size);
    }
}
