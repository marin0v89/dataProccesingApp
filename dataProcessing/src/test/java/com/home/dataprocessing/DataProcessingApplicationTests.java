package com.home.dataprocessing;

import com.home.dataprocessing.service.CsvDataProcessService;
import com.home.dataprocessing.util.DataConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DataProcessingApplicationTests {

    @Autowired
    private CsvDataProcessService csvReader;

    @Test
    void contextLoads() throws IOException {

        csvReader.getAllRecords();
    }

    @Test
    public void testConvert() {
        DataConverter converter = new DataConverter();

        assertEquals(0.0, converter.convert("-"));
        assertEquals(123.45, converter.convert("123,45"));
        assertEquals(123.45, converter.convert("123.45"));
        assertEquals(0.0, converter.convert(""));
        assertEquals(12345.0, converter.convert("12 345"));
        assertEquals(-0.003, converter.convert("-0.003"));
        assertEquals(-0.003, converter.convert("-0,003"));
    }

    @Test
    public void testInvalidConvert() {
        DataConverter converter = new DataConverter();

        assertThrows(RuntimeException.class, () -> {
            converter.convert("invalid");
        });

    }
}
