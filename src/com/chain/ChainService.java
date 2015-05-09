package com.chain;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChainService {
	
	private static AtomicInteger counter = new AtomicInteger();
	private Log log = LogFactory.getLog(ChainService.class);
	
	public String helloChain(String name) {
		log.info("ChainService start .....");
		int count = counter.incrementAndGet();
		log.info("count : " + count);
		return "hello " + name + " stay foolish stay hungry !";
	}
}
