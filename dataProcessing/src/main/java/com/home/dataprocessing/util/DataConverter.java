package com.home.dataprocessing.util;

import com.opencsv.bean.AbstractBeanField;

public class DataConverter extends AbstractBeanField<Double, String> {

    @Override
    public Double convert(String value) {
        if (value == null || value.isEmpty() || "-".equals(value)) {
            return 0.0;
        }
        value = value.replaceAll("[^\\d.]", "");

        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to convert value: " + value, e);
        }
    }
}
