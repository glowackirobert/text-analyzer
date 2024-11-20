package com.glowackirobert.analyzer.service;

import com.glowackirobert.analyzer.dto.Occurrence;

public interface AnalyzerService {

    Occurrence retrieveOccurrences(String input, String query);
}
