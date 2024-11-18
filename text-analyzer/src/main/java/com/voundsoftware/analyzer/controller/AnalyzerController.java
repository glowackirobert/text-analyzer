package com.voundsoftware.analyzer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voundsoftware.analyzer.dto.Occurrence;
import com.voundsoftware.analyzer.service.AnalyzerService;

@RestController
@RequestMapping("/api/v1")
public class AnalyzerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyzerController.class);

    private final AnalyzerService analyzerService;

    public AnalyzerController(AnalyzerService analyzerService) {
        this.analyzerService = analyzerService;
    }

    @GetMapping("/search")
    public ResponseEntity<Occurrence> searchOccurrences(@RequestParam String input, @RequestParam String query) {
        LOGGER.info("searchOccurrences(input: {}, query: {})", input, query);
        if (input.isBlank()) {
            LOGGER.warn("User input is blank");
            return ResponseEntity.unprocessableEntity()
                .build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                .build();
        }
    }
}
