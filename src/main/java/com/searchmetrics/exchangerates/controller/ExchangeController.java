package com.searchmetrics.exchangerates.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.searchmetrics.exchangerates.model.Exchange;
import com.searchmetrics.exchangerates.service.ExchangeService;

/**
 * The Class ExchangeController.
 */
@Controller
@RequestMapping("/rates")
public class ExchangeController {
	
	/** The exchange service. */
	@Autowired
    private ExchangeService exchangeService;

	/**
     * Endpoint that get the latest exchange rate from one currency to another.
     *
     * @param from origin currency code.
     * @param to  destination currency code.
     * @return exchange rate from one currency to another
     * support BTC to USD conversion
     */
    @GetMapping
    @ResponseBody
    public Exchange latest(@RequestParam(value = "from", defaultValue = "BTC") String from,
                                @RequestParam(value = "to", defaultValue = "USD") String to) {
        return exchangeService.getLastExchange(from, to);
    }
    
    /**
     * Endpoint that get the historical exchange rate from BTC to USD during the given period.
     *
     * @param start the start date to be used as filter.
     * @param end   the end date to be used as filter.
     * @return the list of the exchange rates.
     * @throws ParseException 
     * support BTC to USD conversion
     */
    @GetMapping("/historical")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Exchange> between(@RequestParam(value = "from", defaultValue = "BTC") String from,
                                                      @RequestParam(value = "to", defaultValue = "USD") String to,
                                                      @RequestParam(value = "start") String start,
                                                      @RequestParam(value = "end") String end) throws ParseException {

    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return exchangeService.getAllBetween(from, to, format.parse(start), format.parse(end));
    }
}
