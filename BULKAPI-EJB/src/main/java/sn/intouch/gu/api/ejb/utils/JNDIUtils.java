package sn.intouch.gu.api.ejb.utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIUtils {

	private JNDIUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Object lookUpEJB(EJBRegistry ejbRegistry) {
		try {
			InitialContext initialContext = new InitialContext();
			return initialContext.lookup("java:app/BULKAPI-EJB/" + ejbRegistry.name());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
