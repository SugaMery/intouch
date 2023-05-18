package sn.intouch.gu.api.config;

import org.apache.log4j.Logger;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import sn.intouch.gu.api.entities.TransactionExchange;

public class FilterBulk extends ServerResource{
	
	private static Logger log= Logger.getLogger(FilterBulk.class);

	@Get("json")
	public TransactionExchange filter() {
		
		Gson gson = new Gson();
		TransactionExchange te = new TransactionExchange();
		String exc = getQueryValue("exchange");
		try {
			log.info("---------- STARTED REQUEST ------------");
			log.info(exc);
			te = gson.fromJson(exc, TransactionExchange.class);
			log.info("----------- END REQUEST --------------");
		} catch (Exception e) {
			log.error("", e);
			te.setErrorCode("400");
			te.setErrorMessage("Erreur parsing json");
			te.getServiceExchange().getInfosSup().put("sms", "Erreur parsing json");
			return te;
		}

		try {

			
			if (te.getServiceExchange() != null && te.getServiceExchange().getInfosSup() != null) {
				te.setErrorCode("200");
				te.setErrorMessage("SUCCESS");

			} else {
				te.setErrorCode("400");
				te.setErrorMessage("Mauvaise requete");
				te.getServiceExchange().getInfosSup().put("sms", "Mauvaise requete");
			}

		} catch (Exception e) {
			log.error("", e);
		}

		return te;
		
	}

}
