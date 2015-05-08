package client;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;

public class CallAsis {
	public static void main(String[] args) {
		try {
			String url = "http://localhost:8080/axis/HelloWS.jws";
			Service service = new Service();
			Call call = service.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(url, "printWS"));
			String result = (String)call.invoke(new Object[]{"foolish","hungry"});
			System.out.println("client " + result);
		} catch (ServiceException | RemoteException e) {
			e.printStackTrace();
		}
	}
}
