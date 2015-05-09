package com.chain;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Chain1Handler extends BasicHandler {
	
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(Chain1Handler.class);

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		log.info("Chain1Handler start......");
		String greeting = (String)this.getOption("name");
		log.info("Chain1Handler :  hello I am " + greeting + " stay foolish stay hungry !");
	}
}
