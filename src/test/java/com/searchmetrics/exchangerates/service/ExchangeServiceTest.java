package com.searchmetrics.exchangerates.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.searchmetrics.exchangerates.model.Exchange;
import com.searchmetrics.exchangerates.repository.ExchangeRepository;

@SpringBootTest
public class ExchangeServiceTest {

	@Autowired
    private ExchangeService exchangeService;
    
	@Mock
	private ExchangeRepository exchangeRepository;
    
	private Exchange exchange =  new Exchange();
	
	private List<Exchange> exchangeList =  new ArrayList<>();

    @Test
    public void testGetLastExchangeRate() {
        when(exchangeRepository.findFirstByOrderByCreatedTimeDesc()).thenReturn(Optional.of(exchange));

        final Exchange lastExchange = exchangeService.getLastExchange("BTC", "USD");
        assertThat(lastExchange.getRates(),  Matchers.comparesEqualTo(BigDecimal.valueOf(8775.11)));
    }
    
    @Test
    public void testHistoricalExchangeRate() throws ParseException {
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date start = format.parse("2020-05-09");
    	Date end = format.parse("2020-05-10");
        when(exchangeRepository.findByCreatedTimeBetween(start, end)).thenReturn(exchangeList);
        final List<Exchange> lastExchange = exchangeService.getAllBetween("BTC", "USD", start, end);
        assertThat(lastExchange, hasSize(9));
    }
}