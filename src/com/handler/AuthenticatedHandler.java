package com.handler;

import java.io.IOException;

import javax.xml.soap.SOAPException;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.axis.security.simple.SimpleSecurityProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthenticatedHandler extends BasicHandler {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(AuthenticatedHandler.class);

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		log.info("AuthenticatedHandler start........");
		
//		Handler service = messageContext.getService();
		
		String username = messageContext.getUsername();
		if(username == null) {
			throw new AxisFault("authenticated fail : connot indicate user information !");
		}
		
		String password = messageContext.getPassword();
		
		log.info("username : " + username +  " password : "  + password);
		
		SecurityProvider securityProvider = (SecurityProvider) messageContext.getProperty(MessageContext.SECURITY_PROVIDER);
		if (securityProvider == null) {
			securityProvider = new SimpleSecurityProvider();
			messageContext.setProperty(MessageContext.SECURITY_PROVIDER, securityProvider);
		}

		AuthenticatedUser authenticatedUser = securityProvider.authenticate(messageContext);
		if(authenticatedUser == null) {
			throw new AxisFault("authenticated fail ! ");
		}
		messageContext.setProperty(MessageContext.AUTHUSER, authenticatedUser);
		try {
			messageContext.getMessage().writeTo(System.out);
			System.out.println();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("authenticated success !");

	}

}
