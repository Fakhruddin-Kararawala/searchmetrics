package com.searchmetrics.exchangerates.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Configuration properties that are used to define the integration variables with the coinmarketcap endpoint.
 *
 * @see <a href="https://www.coinmarketcap.com/">Coinmarketcap</a>
 */
@Configuration
@ConfigurationProperties("coinmarketcap")
@Data
@NoArgsConstructor
@Getter
public class CoinMarketCapConfig {

    private String endpoint;
    private String apiKey;
    private String currencies;
    private String cron;
}
