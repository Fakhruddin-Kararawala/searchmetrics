package com.searchmetrics.exchangerates.service.collector;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.searchmetrics.exchangerates.config.CoinMarketCapConfig;
import com.searchmetrics.exchangerates.model.Exchange;
import com.searchmetrics.exchangerates.repository.ExchangeRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;

/**
 * The Class CoinMarketCapExchangeRateCollector.
 * Responsible for fetching exchange data from CoinMarket API 
 */
@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CoinMarketCapExchangeRateCollector implements ExchangeRateCollector {

	/** The coin market cap config. */
	@Autowired
    private CoinMarketCapConfig coinMarketCapConfig;
	
	/** The exchange repo. */
	@Autowired
    private ExchangeRepository exchangeRepo;
	
    /** The rest template. */
    private final RestTemplate restTemplate = new RestTemplate();
    
//    /** The logger. */
//    @Autowired
//    private Logger logger;

	/**
	 * Collect.
	 * Configurable scheduling to fetch exchange data
	 */
	@SuppressWarnings("serial")
	@Scheduled(cron = "${coinmarketcap.cron}")
	@Override
	public void collect() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(new ArrayList<MediaType>() {
			{
				add(MediaType.APPLICATION_JSON);
			};
		});

		httpHeaders.add("X-CMC_PRO_API_KEY", coinMarketCapConfig.getApiKey());
		final HttpEntity httpEntity = new HttpEntity<>(null, httpHeaders);

		final String uri = UriComponentsBuilder.fromUriString(coinMarketCapConfig.getEndpoint())
				.queryParam("convert", coinMarketCapConfig.getCurrencies()).toUriString();

		final ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

		if (null != response && response.getStatusCode() == HttpStatus.OK) {
			JSONObject json = JSONObject.fromObject(response.getBody());
			Double exchangePrice = json.getJSONArray("data").getJSONObject(Integer.valueOf(0)).getJSONObject("quote").getJSONObject(coinMarketCapConfig.getCurrencies()).getDouble("price");
			System.out.println("latest exchange Price " + exchangePrice);
			BigDecimal bigDecimal = BigDecimal.valueOf(exchangePrice);
			Exchange exchange = new Exchange();
			exchange.setFromCurrency("BTC");
			exchange.setToCurrency(coinMarketCapConfig.getCurrencies());
			exchange.setRates(bigDecimal.setScale(10, BigDecimal.ROUND_HALF_EVEN));
			exchangeRepo.save(exchange);
		}
	}
}
