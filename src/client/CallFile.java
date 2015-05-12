package client;

import java.io.File;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.Handler;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.JAFDataHandlerDeserializerFactory;
import org.apache.axis.encoding.ser.JAFDataHandlerSerializerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class CallFile {

	private static final Log log = LogFactory.getLog(CallFile.class);
	String url = "http://localhost:8080/axis/services/FileService";
	private QName qname = new QName(url, "DataHandler");
	Call call = null;
	
	public CallFile() {
		log.info("CallFile start....");
		Service service = new Service();
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			// 在wsdl文件中,注册DataHandler的类型
			call.registerTypeMapping(Handler.class, qname, JAFDataHandlerSerializerFactory.class, JAFDataHandlerDeserializerFactory.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CallFile callFile = new CallFile();
		callFile.testUp();
	}

	public void testUp() {
		try {
			call.setOperationName(new QName(url,"uploadFile"));
			call.setReturnType(XMLType.SOAP_STRING);
			call.addParameter("filename", XMLType.SOAP_STRING, ParameterMode.IN);
			call.addParameter("dataHandler", qname, ParameterMode.IN);

			FileDataSource fileDataSource = new FileDataSource("a.txt");
			DataHandler dataHandler = new DataHandler(fileDataSource);

			String result = (String) call.invoke(new Object[] { "a.txt", dataHandler });
			log.info("upload result : " + result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testDown() {
		try {
			// String dwDir = "G:/downRepository";
			// DataHandler[] downloadFile = fileService.downloadFile("a.txt");
			// log.info(downloadFile.length);
			// for (int i = 0; i < downloadFile.length; i++) {
			// DataHandler dataHandler = downloadFile[i];
			// InputStream inputStream = dataHandler.getDataSource().getInputStream();
			// FileOutputStream fileOutputStream = new FileOutputStream(new File(dwDir,"a.txt"));
			// byte[] readBuffer = new byte[1024];
			// int n = 0;
			// while((n=inputStream.read(readBuffer)) != -1) {
			// fileOutputStream.write(readBuffer, 0, n);
			// }
			// fileOutputStream.flush();
			// fileOutputStream.close();
			// inputStream.close();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
