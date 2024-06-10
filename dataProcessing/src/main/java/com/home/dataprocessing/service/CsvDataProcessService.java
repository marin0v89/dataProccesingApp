package com.home.dataprocessing.service;

import com.home.dataprocessing.model.CsvDataDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvDataProcessService {

    @Value("${csv.file.path}")
    private String csvFilePath;

    public List<CsvDataDTO> getAllRecords() throws IOException {
        try (FileReader reader = new FileReader(csvFilePath)) {
            return new CsvToBeanBuilder<CsvDataDTO>(reader)
                    .withType(CsvDataDTO.class)
                    .build()
                    .parse();
        }
    }

    public double calculateTotalCost(List<CsvDataDTO> records) {
        return records.stream()
                .mapToDouble(CsvDataDTO::getCost)
                .sum();
    }

    public List<CsvDataDTO> filterRecordsByTime(List<CsvDataDTO> records, String startTime, String endTime) {
        return records.stream()
                .filter(record -> (startTime == null || record.getUsageStartTime().compareTo(startTime) >= 0) &&
                        (endTime == null || record.getUsageEndTime().compareTo(endTime) <= 0))
                .collect(Collectors.toList());
    }

    public List<CsvDataDTO> filterRecordsByLocation(List<CsvDataDTO> records, String location) {
        return records.stream()
                .filter(record -> location == null || location.equals(record.getLocationLocation()))
                .collect(Collectors.toList());
    }

    public List<CsvDataDTO> filterRecordsBySku(List<CsvDataDTO> records, String sku) {
        return records.stream()
                .filter(record -> sku == null || sku.equals(record.getSkuId()))
                .collect(Collectors.toList());
    }
}
