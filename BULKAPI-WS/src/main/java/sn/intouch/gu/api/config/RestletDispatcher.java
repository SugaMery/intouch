package sn.intouch.gu.api.config;


import org.apache.log4j.Logger;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.engine.Engine;
import org.restlet.ext.gson.GsonConverter;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

import sn.intouch.gu.api.ejb.entities.Parametre;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;

public class RestletDispatcher extends Application {
	ParameterService parameterService = (ParameterService) JNDIUtils.lookUpEJB(EJBRegistry.ParameterServiceBean);
	private static Logger LOG = Logger.getLogger(RestletDispatcher.class);

	@Override
	public synchronized Restlet createInboundRoot() {
			// Guard the restlet with BASIC authentication.
		ChallengeAuthenticator guard = new ChallengeAuthenticator(null, ChallengeScheme.HTTP_BASIC, "testRealm");
		// Instantiates a Verifier of identifier/secret couples based on a
		// simple Map.
		MapVerifier mapVerifier = new MapVerifier();
		// Load a single static login/secret pair.
		//Basic dXV1dVVbI2d1MWNoM3RVTklRVUUjXVh4eHh4eHh4MDk6QUl6YVN5QlpqMEZJOFVhbV93RDMxemRwcTJEN0dsREpQXzRtdXhBX19fMjM0UkFobWFuYXJl
		mapVerifier.getLocalSecrets().put("uuuuU[#gu1ch3tUNIQUE#]Xxxxxxxx09",
				"AIzaSyBZj0FI8Uam_wD31zdpq2D7GlDJP_4muxA___234RAhmanare".toCharArray());
		guard.setVerifier(mapVerifier);

		final Router router = new Router(getContext());
		router.setDefaultMatchingMode(Template.MODE_EQUALS);
		Engine.getInstance().getRegisteredConverters().add(new GsonConverter());
		router.attach("/initiatepayment", InitiateBulkPayment.class);
		router.attach("/initiatecashbulk", InitiateCashBulk.class);
		router.attach("/filter/{codeService}/{receiverMsisdn}/{amount}", FilterBulk.class);
		router.attach("/transaction", TransactionSuccess.class);
		router.attach("/ussdoperation", UssdOperation.class);
		router.attach("/ussdoperationmois", UssdOperationMois.class);
		router.attach("/operation", ActiviteOperation.class);
		router.attach("/authentification", Authentification.class);
		router.attach("/changepassword", ChangePassword.class);
		router.attach("/nbFailedOperation", NbUssdFailed.class);
		router.attach("/updateUser", UpdateUser.class);
		router.attach("/getUserByMail", GetUserByMail.class);
                router.attach("/getFraisByAgence", GetFraisByAgence.class);

		
		
		Swagger2SpecificationRestlet swagger2SpecificationRestlet = new Swagger2SpecificationRestlet(this);
		Parametre parametre = parameterService.getParameterByCode("URL_DOC_DIST_BULK");
		String urlSer = "";
		if (parametre == null) {
			urlSer = "http://localhost:9090/bo/bulk/api/v1";
		} else if (parametre.getPrmStringValue() == null) {
			urlSer = "http://localhost:9090/bo/bulk/api/v1";
		} else {
			urlSer = parametre.getPrmStringValue();
			LOG.info("Parametre URL_DOC_DIST_BULK: " + urlSer);
		}
		swagger2SpecificationRestlet.setBasePath(urlSer);
		swagger2SpecificationRestlet.attach(router, "/docs");
		guard.setNext(router);
		return guard;
	}

}
