package com.glowackirobert.analyzer.controller;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class AnalyzerControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenUserInput_whenTriggerController_thenReturnOccurrencesAndStatus200() throws Exception {
        String input = "The quick brown fox jumps over the lazy dog";
        String query = "quick, fox, dog";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("input", input)
                .param("query", query))
            .andExpect(status().isOk())
            .andExpect(jsonPath("input").value(input))
            .andExpect(jsonPath("values", hasSize(3)))
            .andExpect(jsonPath("values", contains("quick", "fox", "dog")));
    }

    @Test
    public void givenNoUserInput_whenTriggerController_thenReturn4xxResponse() throws Exception {
        String query = "user, word";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("query", query))
            .andExpect(status().isBadRequest());
    }
}