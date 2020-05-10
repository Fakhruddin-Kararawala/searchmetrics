package com.searchmetrics.exchangerates.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchmetrics.exchangerates.model.Exchange;


/**
 * The Interface ExchangeRepository.
 * Responsible for fetching data from database.
 */
@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
	
	/**
	 * Find first by order by created time desc.
	 *
	 * @return the optional
	 */
	Optional<Exchange> findFirstByOrderByCreatedTimeDesc();
	
	/**
	 * Find by created time between.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the list
	 */
	List<Exchange> findByCreatedTimeBetween(Date start,Date end);
}
