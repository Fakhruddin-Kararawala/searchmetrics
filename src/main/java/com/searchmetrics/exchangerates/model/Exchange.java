package com.searchmetrics.exchangerates.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity that maps an exchange rate.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "exchange")
public class Exchange {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "from_currency")
    private String fromCurrency;

	@Column(name = "to_currency")
    private String toCurrency;
    
    private BigDecimal rates;
    
    @Column(name = "created_time", nullable = false, updatable = false)
	@CreatedDate
	private Date createdTime;
    
    
}

