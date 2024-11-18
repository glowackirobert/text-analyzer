package com.voundsoftware.analyzer.service;

import com.voundsoftware.analyzer.dto.Occurrence;

public interface AnalyzerService {

    Occurrence retrieveOccurrences(String input, String query);
}
