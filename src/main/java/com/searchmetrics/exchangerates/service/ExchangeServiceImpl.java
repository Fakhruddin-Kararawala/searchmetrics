package com.searchmetrics.exchangerates.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchmetrics.exchangerates.exception.ExchangeNotFoundException;
import com.searchmetrics.exchangerates.model.Exchange;
import com.searchmetrics.exchangerates.repository.ExchangeRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class ExchangeServiceImpl.
 */
@Service
@RequiredArgsConstructor
class ExchangeServiceImpl implements ExchangeService {

	/** The exchange repository. */
	@Autowired
    private ExchangeRepository exchangeRepository;

    /**
     * Gets the last exchange.
     *
     * @param from the from
     * @param to the to
     * @return the last exchange
     */
    @Override
    public Exchange getLastExchange(String from, String to) {
        final Optional<Exchange> lastExchange = exchangeRepository.findFirstByOrderByCreatedTimeDesc();
        return lastExchange.orElseThrow(ExchangeNotFoundException::new);
    }

    /**
     * Gets the all between.
     *
     * @param from the from
     * @param to the to
     * @param start the start
     * @param end the end
     * @return the all between
     */
    @Override
    public List<Exchange> getAllBetween(String from, String to, Date start, Date end) {
        final List<Exchange> byTimestampBetween = exchangeRepository.findByCreatedTimeBetween(start, end);
        return byTimestampBetween;
    }
	
}
