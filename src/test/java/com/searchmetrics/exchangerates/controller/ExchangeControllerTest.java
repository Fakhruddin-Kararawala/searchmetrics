package com.searchmetrics.exchangerates.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testLatestRates() throws Exception {
        mvc.perform(get("/rates")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testHistoricalRates() throws Exception {
        mvc.perform(get("/rates/historical")
                .param("start", "2020-05-09")
                .param("end", "2020-05-10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
