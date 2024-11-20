package com.glowackirobert.analyzer.mapper;

import static com.glowackirobert.analyzer.mapper.OccurrenceMapper.toOccurrenceMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.glowackirobert.analyzer.dto.Occurrence;

class OccurrenceMapperTest {

    @Test
    void givenInputAndWords_thenReturnOccurrence() {
        String input = "the quick brown fox jumps over the lazy dog";
        List<String> words = Arrays.asList("the", "fox", "dog");

        Occurrence result = toOccurrenceMapper().apply(input, words);

        assertNotNull(result);
        assertEquals(input, result.input());
        assertEquals(words, result.values());
    }

    @Test
    void givenNoInputAndNoWords_thenReturnEmptyOccurrence() {
        String input = "";
        List<String> words = List.of();

        Occurrence result = toOccurrenceMapper().apply(input, words);

        assertNotNull(result);
        assertEquals(input, result.input());
        assertEquals(words, result.values());
    }
}
