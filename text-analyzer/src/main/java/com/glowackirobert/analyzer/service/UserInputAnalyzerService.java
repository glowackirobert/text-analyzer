package com.glowackirobert.analyzer.service;

import static com.glowackirobert.analyzer.mapper.OccurrenceMapper.toOccurrenceMapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.glowackirobert.analyzer.dto.Occurrence;

@Service
public class UserInputAnalyzerService implements AnalyzerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputAnalyzerService.class);

    private static final String COMMA_REGEX = ",";

    public Occurrence retrieveOccurrences(String input, String query) {
        LOGGER.info("retrieveOccurrences(input: {}, query:{})", input, query);
        var words = multipleWordsSplitter(query);
        List<String> occurrences = new ArrayList<>();
        for (String word : words) {
            var index = input.indexOf(word.trim());
            while (index != -1) {
                occurrences.add(word.trim());
                index = input.indexOf(word, index + word.length());
            }
        }
        Occurrence result = toOccurrenceMapper().apply(input, occurrences);
        LOGGER.info("retrieveOccurrences(...) = {}", occurrences);
        return result;
    }

    private String[] multipleWordsSplitter(String query) {
        return query.split(COMMA_REGEX);
    }
}
