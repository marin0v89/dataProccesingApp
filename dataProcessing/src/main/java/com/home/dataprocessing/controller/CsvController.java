package com.home.dataprocessing.controller;

import com.home.dataprocessing.model.CsvDataDTO;
import com.home.dataprocessing.service.CsvDataProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

        records = records.stream()
                .filter
                        (
                                record ->
                                        (startTime == null || record.getUsageStartTime().compareTo(startTime) >= 0) &&
                                                (endTime == null || record.getUsageEndTime().compareTo(endTime) <= 0) &&
                                                (location == null || location.equals(record.getLocationLocation())) &&
                                                (sku == null || sku.equals(record.getSkuId()))).collect(Collectors.toList()
                );

        return csvService.calculateTotalCost(records);
    }
}
