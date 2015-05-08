package client;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import com.wsdd.Order;

public class CallWSDD {
	public static void main(String[] args) throws ServiceException, RemoteException {
		String url = "http://localhost:8080/axis/services/OrderService";
		Service service = new Service();
		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress(url);
		
		Order order = new Order();
		order.setId(1);
		
		QName qn = new QName("urn:BeanService","Order");
		call.registerTypeMapping(Order.class, qn, new BeanSerializerFactory(Order.class, qn), new BeanDeserializerFactory(Order.class, qn));
		
		call.addParameter("arg1", qn, ParameterMode.IN);
		call.setReturnType(qn, Order.class);
		
		call.setOperationName(new QName(url,"returnOrder"));
		
		Order returnOrder = (Order)call.invoke(new Object[]{order});
		if(returnOrder != null) {
			System.out.println(returnOrder.getName());
		}
		
	}
}
