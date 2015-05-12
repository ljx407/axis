package com.chain;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Chain2Handler extends BasicHandler {
	
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(Chain2Handler.class);

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		log.info("Chain2Handler start......");
		String greeting = (String)this.getOption("name");
		log.info("Chain2Handler : hello I am " + greeting + " stay foolish stay hungry !");
	}
}
