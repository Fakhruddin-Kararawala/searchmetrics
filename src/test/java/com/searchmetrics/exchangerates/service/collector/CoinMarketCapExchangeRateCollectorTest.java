package com.searchmetrics.exchangerates.service.collector;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.searchmetrics.exchangerates.config.CoinMarketCapConfig;

@SpringBootTest
public class CoinMarketCapExchangeRateCollectorTest {

	@Autowired
	CoinMarketCapConfig coinMarketCapConfig;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testCollect() {
    	Mockito.when(restTemplate.getForEntity(coinMarketCapConfig.getEndpoint(), String.class))
        .thenReturn(new ResponseEntity(String.class, HttpStatus.OK));
    }

}