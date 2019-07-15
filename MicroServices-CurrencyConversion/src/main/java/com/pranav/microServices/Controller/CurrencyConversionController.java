package com.pranav.microServices.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pranav.microServices.Bean.CurrencyConversionBean;
import com.pranav.microServices.FeignProxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/currency-converter/from/{from}/to/{to}/ammount/{ammount}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal ammount) {

		String url = "http://localhost:8001/currency-exchange/from/{from}/to/{to}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(url,
				CurrencyConversionBean.class, uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		logger.info("{} -> ", response);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), ammount,
				ammount.multiply(response.getConversionMultiple()), response.getPort());

	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/ammount/{ammount}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal ammount) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), ammount,
				ammount.multiply(response.getConversionMultiple()), response.getPort());

	}

}
