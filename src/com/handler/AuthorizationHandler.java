package com.handler;

import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthorizationHandler extends BasicHandler {
	
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(AuthorizationHandler.class);
	
	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		log.info("AuthorizationHandler start .......");
		AuthenticatedUser authenticatedUser = (AuthenticatedUser)messageContext.getProperty(MessageContext.AUTHUSER);
		if(authenticatedUser == null) {
			throw new AxisFault("Authorization fail : connot include a authenticated user !");
		}
		log.info(authenticatedUser.getName());
		Handler service = messageContext.getService();
		String allowedRoles = (String)service.getOption("allowedRoles");
		if(allowedRoles == null) {
			log.info("all authenticatedUser can visit the service :" + service.getName());
			return ;
		}
		List<String> roles = Arrays.asList(allowedRoles.split(","));
		if(!roles.contains(authenticatedUser.getName())) {
			log.info("authorization fail : you have not the authorization !");
			throw new AxisFault("authorization fail : you have not the authorization !");
		} 
		log.info("authorization success !");
				
	}
	
	

}
