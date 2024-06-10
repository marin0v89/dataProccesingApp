package com.home.dataprocessing;

import com.home.dataprocessing.util.DataConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataConverterTest {
    private DataConverter dataConverter;

    @BeforeEach
    public void setUp() {
        dataConverter = new DataConverter();
    }

    @Test
    public void testConvertValidNumber() {
        assertEquals(10.0, dataConverter.convert("10.0"));
        assertEquals(0.0, dataConverter.convert("0"));
        assertEquals(1000.5, dataConverter.convert("1,000.5"));
    }

    @Test
    public void testConvertInvalidNumber() {
        assertThrows(RuntimeException.class, () -> dataConverter.convert("invalid"));
    }

    @Test
    public void testConvertNull() {
        assertEquals(0.0, dataConverter.convert(null));
    }

    @Test
    public void testConvertEmptyString() {
        assertEquals(0.0, dataConverter.convert(""));
    }

    @Test
    public void testConvertDash() {
        assertEquals(0.0, dataConverter.convert("-"));
    }

    @Test
    public void testConvertNonNumericCharacters() {
        assertEquals(10.0, dataConverter.convert("$10.0"));
        assertEquals(1000.5, dataConverter.convert("1,000.5 USD"));
    }
}
