package com.glowackirobert.analyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.glowackirobert.analyzer.dto.Occurrence;

class UserInputAnalyzerServiceTest {

    @Test
    void givenServiceInstance_whenInputAndQueryAreEmptyStrings_shouldReturnEmptyOccurrence() {
        UserInputAnalyzerService service = new UserInputAnalyzerService();

        Occurrence result = service.retrieveOccurrences("", "");

        assertNotNull(result);
        assertEquals("", result.input());
        assertTrue(result.values()
            .isEmpty());
    }

    @Test
    void givenServiceInstance_whenSingleWordInQuery_shouldReturnCorrectOccurrences() {
        UserInputAnalyzerService service = new UserInputAnalyzerService();

        String input = "The quick brown fox jumps over the lazy dog";
        String query = "the";

        Occurrence result = service.retrieveOccurrences(input, query);

        assertNotNull(result);
        assertEquals(input, result.input());
        assertEquals(1, result.values()
            .size());
        assertTrue(result.values()
            .contains("the"));
    }

    @Test
    void givenServiceInstance_whenMultipleWordsInQuery_shouldReturnCorrectOccurrences() {
        UserInputAnalyzerService service = new UserInputAnalyzerService();

        String input = "The quick brown fox jumps over the lazy dog. The fox is quick and brown.";
        String query = "quick, fox, the";

        Occurrence result = service.retrieveOccurrences(input, query);

        assertNotNull(result);
        assertEquals(input, result.input());
        assertEquals(5, result.values()
            .size());
        assertEquals(2, result.values()
            .stream()
            .filter(word -> word.equals("quick"))
            .count());
        assertEquals(2, result.values()
            .stream()
            .filter(word -> word.equals("fox"))
            .count());
        assertEquals(1, result.values()
            .stream()
            .filter(word -> word.equals("the"))
            .count());
    }

    @Test
    void givenServiceInstance_whenQueryWordsAreRepeatedInTheInput_shouldReturnCorrectOccurrences() {
        UserInputAnalyzerService service = new UserInputAnalyzerService();

        String input = "The cat and the dog played. The cat chased the dog, and the dog ran.";
        String query = "the, cat, dog";

        Occurrence result = service.retrieveOccurrences(input, query);

        assertNotNull(result);
        assertEquals(input, result.input());
        assertEquals(8, result.values()
            .size());
        assertEquals(3, result.values()
            .stream()
            .filter(word -> word.equals("the"))
            .count());
        assertEquals(2, result.values()
            .stream()
            .filter(word -> word.equals("cat"))
            .count());
        assertEquals(3, result.values()
            .stream()
            .filter(word -> word.equals("dog"))
            .count());
    }

    @Test
    void givenServiceInstance_whenQueriesWithSpecialCharacters_shouldReturnCorrectOccurrences() {
        UserInputAnalyzerService service = new UserInputAnalyzerService();

        String input = "The quick-brown fox jumps over the lazy dog. The fox is quick and brown!";
        String query = "quick-brown, fox";

        Occurrence result = service.retrieveOccurrences(input, query);

        assertNotNull(result);
        assertEquals(input, result.input());
        assertEquals(3, result.values()
            .size());
        assertEquals(1, result.values()
            .stream()
            .filter(word -> word.equals("quick-brown"))
            .count());
        assertEquals(2, result.values()
            .stream()
            .filter(word -> word.equals("fox"))
            .count());
    }
}