package sn.intouch.gu.api.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import sn.intouch.gu.api.ejb.entities.Parametre;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.entities.ExchangeOperation;
import sn.intouch.gu.api.entities.TransactionExchange;
import sn.intouch.gu.api.utils.Utils;

public class BULKAPIRequest {

	private static Logger log = Logger.getLogger(BULKAPIRequest.class);
	private static ParameterService parametreService = (ParameterService) JNDIUtils
			.lookUpEJB(EJBRegistry.ParameterServiceBean);
	
	public static TransactionExchange createUSSD(TransactionExchange transactionExchange) {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		TransactionExchange tr = new TransactionExchange();
		 tr = transactionExchange;
		final Gson gson = new Gson();
		String proxyHost = null;
		String proxyPort = null;
		
		try {
			Parametre parametre = parametreService.getParameterByCode("URL_GU_BROKER_TRANSACTION");
			log.info("URL_GU_BROKER_TRANSACTION: "+parametre.getPrmStringValue());
			
			String host=parametreService.getParameterByCode("REQUEST_HOST_GU").getPrmStringValue();
			Integer port=Integer.valueOf(parametreService.getParameterByCode("REQUEST_PORT_GU").getPrmStringValue());
			String protocole=parametreService.getParameterByCode("REQUEST_PROTOCOLE_GU").getPrmStringValue();

			HttpHost target = new HttpHost(host, port, protocole);
			//La valeur
			HttpPut request = new HttpPut(parametre.getPrmStringValue());
						
			if (parametre.getPrmValue()==1){
				try {
					proxyHost = parametreService.getParameterByCode("proxy_host").getPrmStringValue();
					proxyPort = parametreService.getParameterByCode("proxy_port").getPrmStringValue();
				} catch (Exception e) {
					log.error("Erreur lors de la recuperation de proxy_host et proxy port");
				}
				if (proxyHost != null && proxyPort != null) {
					log.info("##### Using Proxy #####");
					HttpHost proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
					RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
					request.setConfig(config);
				}
			}
			
			String auth = "uuuuU[#gu1ch3tUNIQUE#]Xxxxxxxx09" + ":"
					+ "AIzaSyBZj0FI8Uam_wD31zdpq2D7GlDJP_4muxA___234RAhmanare";
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
			String authHeader = "Basic " + new String(encodedAuth);
			request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			request.addHeader("Content-Type", "application/json");
			
			/* Prepare StringEntity from JSON */
			StringEntity jsonData = new StringEntity(gson.toJson(transactionExchange), "UTF-8");

			/* Body of request */
			request.setEntity(jsonData);

			try {
				CloseableHttpResponse httpResponse = httpclient.execute(target,request);
				HttpEntity httpEntity = httpResponse.getEntity();

				log.info("Error Code Creation operation: "+httpResponse.getStatusLine().getStatusCode());
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String entity = EntityUtils.toString(httpEntity);
					try {
						tr = gson.fromJson(entity, TransactionExchange.class);
						
					} catch (Exception e) {
						tr.setErrorCode("500"); //Erreur de traitement

						log.error("", e);
					}

				} else {
					tr.setErrorCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
					request.abort();
				}
			} catch (Exception e) {
				tr.setErrorCode("500"); //Erreur de traitement
				log.error("", e);
			}
		} catch (Exception e) {
			log.error("", e);
			tr.setErrorCode("500");
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				log.error("", e);
				tr.setErrorCode("500");
			}
		}
		
		log.info("---------- STARTED RESPONSE TREX SERVER ------------");
		log.info(gson.toJson(tr));
		log.info("---------- END RESPONSE TREX SERVER--------------");
		return tr;
	}
	
	
}
