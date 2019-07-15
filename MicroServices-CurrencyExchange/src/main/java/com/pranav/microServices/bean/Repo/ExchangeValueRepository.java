package com.pranav.microServices.bean.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.microServices.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long>{
	
	ExchangeValue findByFromAndTo(String from,String to);

}
