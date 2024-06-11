package com.home.dataprocessing.service;

import com.home.dataprocessing.model.CsvDataDTO;
import com.home.dataprocessing.model.Pagination;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CsvDataProcessService {

    @Value("${csv.file.path}")
    private String csvFilePath;

    public List<CsvDataDTO> getAllRecords() throws IOException {
        try (FileReader reader = new FileReader(csvFilePath)) {
            CsvToBean<CsvDataDTO> csvToBean = new CsvToBeanBuilder<CsvDataDTO>(reader)
                    .withType(CsvDataDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
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

    public List<Map<String, Object>> calculateCostPerGroup(List<CsvDataDTO> records, List<String> groupBy) {
        Map<String, Map<String, Object>> groupedData = new HashMap<>();

        for (CsvDataDTO record : records) {
            String key = createGroupKey(record, groupBy);
            groupedData.putIfAbsent(key, new HashMap<>());
            Map<String, Object> group = groupedData.get(key);
            group.putIfAbsent("cost", 0.0);
            group.put("cost", (Double) group.get("cost") + record.getCost());

            for (String groupField : groupBy) {
                String fieldValue = getFieldValue(record, groupField);
                group.putIfAbsent(groupField, fieldValue);
            }
        }

        return new ArrayList<>(groupedData.values());
    }

    public Pagination<CsvDataDTO> searchByLabelAndCountry(
            List<CsvDataDTO> records, String key, String value, String country, int page, int size) {

        if (page <= 0 || size <= 0 || size > 20) {
            throw new IllegalArgumentException("Page number must be greater than 0 and size must be between 1 and 20");
        }

        List<CsvDataDTO> filteredRecords = records.stream()
                .filter(record -> record.getLabels() != null &&
                        record.getLabels().containsKey(key) && value.equals(record.getLabels().get(key)) &&
                        (country == null || country.equals(record.getLocationCountry())))
                .collect(Collectors.toList());

        long totalItems = filteredRecords.size();

        List<CsvDataDTO> paginatedRecords = filteredRecords.stream()
                .skip((long) (page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());

        return new Pagination<>(paginatedRecords, page, size, totalItems);
    }

    private String createGroupKey(CsvDataDTO record, List<String> groupBy) {
        return groupBy.stream()
                .map(groupField -> getFieldValue(record, groupField))
                .collect(Collectors.joining("_"));
    }

    private String getFieldValue(CsvDataDTO record, String fieldName) {
        switch (fieldName) {
            case "date":
                return record.getUsageStartTime().split(" ")[0];
            case "country":
                return record.getLocationCountry();
            case "service":
                return record.getServiceId();
            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }
}
