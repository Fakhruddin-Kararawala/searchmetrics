package com.searchmetrics.exchangerates.service;


import java.util.Date;
import java.util.List;

import com.searchmetrics.exchangerates.model.Exchange;

/**
 * The Interface ExchangeService.
 */
public interface ExchangeService {

    
    /**
     * Gets the lastest exchange.
     *
     * @param from the from
     * @param to the to
     * @return the lastest exchange
     */
    Exchange getLastExchange(String from, String to);

    
    /**
     * Gets the all between.
     *
     * @param from the from
     * @param to the to
     * @param start the start
     * @param end the end
     * @return the all between
     */
    List<Exchange> getAllBetween(String from, String to, Date start, Date end);
}
