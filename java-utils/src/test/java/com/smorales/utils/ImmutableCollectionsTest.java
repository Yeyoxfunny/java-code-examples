package com.smorales.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImmutableCollectionsTest {

    @Test
    void testEntry() {
        Map.Entry<String, String> entry = ImmutableCollections.entry("Colombia", "Bogotá");
        assertEquals("Colombia", entry.getKey());
        assertEquals("Bogotá", entry.getValue());
    }

    @Test
    void entryWithNullKeyOrNullValueShouldThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ImmutableCollections.entry(null, ""));
        assertThrows(NullPointerException.class, () -> ImmutableCollections.entry("", null));
    }

    @Test
    void mapOf() {
        Map<String, String> expectedCities = new HashMap<>();
        expectedCities.put("Colombia", "Bogotá");
        expectedCities.put("Argentina", "Buenos Aires");
        expectedCities.put("México", "Ciudad de México");
        expectedCities.put("Chile", "Santiago");
        expectedCities.put("Brasil", "Brasília");

        Set<Map.Entry<String, String>> entrySet = expectedCities.entrySet();
        @SuppressWarnings("unchecked")
        Map.Entry<String, String>[] entries = new Map.Entry[entrySet.size()];
        entrySet.toArray(entries);

        Map<String, String> capitalCities = ImmutableCollections.mapOf(entries);
        assertFalse(capitalCities.isEmpty());
        assertEquals(expectedCities, capitalCities);
    }

    @Test
    void mapOfWithEmptyEntriesShouldReturnEmptyMap() {
        Map<String, String> capitalCities = ImmutableCollections.mapOf();
        assertTrue(capitalCities.isEmpty());
    }

    @Test
    void mapOfWithNullEntries() {
        assertThrows(NullPointerException.class, () -> ImmutableCollections.mapOf(null));
    }

    @Test
    void setOf() {
        String[] countries = {"México", "Brasil", "Argentina", "Chile", "Colombia"};
        Set<String> expected = new HashSet<>(Arrays.asList(countries));

        Set<String> actual = ImmutableCollections.setOf(countries);
        assertEquals(expected, actual);
    }

    @Test
    void setOfWithEmptyValuesShouldReturnEmptySet() {
        Set<String> values = ImmutableCollections.setOf();
        assertTrue(values.isEmpty());
    }

    @Test
    void setOfWithNullValuesShouldThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ImmutableCollections.setOf(null));
    }
}