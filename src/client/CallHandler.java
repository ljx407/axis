package client;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class CallHandler {
	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/axis/services/HandlerService";
		
		Service service = new Service();
		Call call = (Call)service.createCall();
		
		call.setTargetEndpointAddress(new URL(url));
		call.setOperationName(new QName("HandlerService","printGreeting"));
		call.getMessageContext().setUsername("user3");
		call.getMessageContext().setPassword("pass3");
		
		String invoke = (String)call.invoke(new Object[]{"jasonliu"});
		System.out.println(invoke);
		
	}
}
