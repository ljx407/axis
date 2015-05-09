package client;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CallWithChain {
	private static Log log = LogFactory.getLog(CallWithChain.class);

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/axis/services/ChainService";
		Service service = new Service();
		Call call = (Call) service.createCall();

		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName(url, "helloChain"));

		String result = (String) call.invoke(new Object[] { "jasonliu" });
		log.info("client callWithChain :" + result);
	}
}
