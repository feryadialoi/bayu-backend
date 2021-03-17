package dev.feryadi.backend.bayu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestStringTest {
    @Test
    public void testStringTest() {
        String response = "response";
        assertEquals("response", response);
    }

    @Test
    public void testStringNotEqualTest() {
        String response = "respons";
        assertNotEquals("response", response);
    }
}
