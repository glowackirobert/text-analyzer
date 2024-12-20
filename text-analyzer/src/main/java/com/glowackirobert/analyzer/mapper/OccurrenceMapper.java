package com.glowackirobert.analyzer.mapper;

import com.glowackirobert.analyzer.dto.Occurrence;

import java.util.List;
import java.util.function.BiFunction;

public class OccurrenceMapper {

    public static BiFunction<String, List<String>, Occurrence> toOccurrenceMapper() {
        return (input, values) -> Occurrence.builder().input(input).values(values).build();
    }
}
