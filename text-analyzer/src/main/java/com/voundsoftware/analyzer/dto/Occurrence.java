package com.voundsoftware.analyzer.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record Occurrence(String input, List<String> values) {
}
