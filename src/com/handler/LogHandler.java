package com.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.soap.SOAPException;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogHandler extends BasicHandler {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(LogHandler.class);
	private static AtomicInteger accounter = new AtomicInteger();

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		PrintWriter pw = null ;
		FileOutputStream fileOutputStream = null ;
		try {
			log.info("LogHandler start....");
			Handler handler = messageContext.getService();
			String filename = (String) this.getOption("filename");
			if(filename == null) {
				throw new AxisFault("cannot indicate the log file !");
			}
			String accessTime = (String)handler.getOption("accessTime");
			accessTime = (accessTime == null ? "1" : String.valueOf(accounter.incrementAndGet()));
			fileOutputStream = new FileOutputStream(filename,true);
			pw = new PrintWriter(fileOutputStream);
			Date date = new Date();
			String result = "在 :" + date + " service:" + messageContext.getTargetService() + " accessed " + accessTime;
			messageContext.getMessage().writeTo(System.out); //打印soap信息
			System.out.println();
			pw.println(result);
			pw.close();
			handler.setOption("accessTime", accessTime);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
