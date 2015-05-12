package test;

import com.chain.Chain1Handler;
import com.chain.Chain2Handler;
import com.chain.ChainHandler;
import com.chain.ChainService;

public class A {
	public static void main(String[] args) {
//		new HandlerService();
//		new OrderService();
//		new LogHandler();
//		new AuthenticatedHandler();
		new ChainService();
		new Chain1Handler();
		new Chain2Handler();
		new ChainHandler();
	}
}
