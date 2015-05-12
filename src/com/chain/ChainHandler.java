package com.chain;

import org.apache.axis.SimpleChain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChainHandler extends SimpleChain {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ChainHandler.class);

	public ChainHandler() {
		log.info("ChainHandler init start.....");
		Chain1Handler ch1 = new Chain1Handler();
		ch1.setOption("name", "jasonliu");
		Chain2Handler ch2 = new Chain2Handler();
		ch2.setOption("name", "peggy");		
		this.addHandler(ch1);
		this.addHandler(ch2);		
	}

}
