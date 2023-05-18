package sn.intouch.gu.api.config;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.engine.Engine;
import org.restlet.ext.gson.GsonConverter;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class NotificationDispatcher extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a
		Router router = new Router(getContext());

		router.setDefaultMatchingMode(Template.MODE_EQUALS);
		Engine.getInstance().getRegisteredConverters().add(new GsonConverter());
		router.attach("/call", ValidateBulkPay.class);
		return router;
	}
}
