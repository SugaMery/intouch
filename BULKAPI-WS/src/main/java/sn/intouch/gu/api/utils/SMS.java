package sn.intouch.gu.api.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import sn.intouch.gu.api.ejb.entities.Parametre;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClients;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.net.InetSocketAddress;
import java.io.IOException;




public class SMS {
	static Logger logger = Logger.getLogger(SMS.class);

	public static void sendSMS(String telephone, String content) {

		ParameterService parameterService = (ParameterService) JNDIUtils
				.lookUpEJB(EJBRegistry.ParameterServiceBean);

		String proxyHost = null;
		String proxyPort = null;
		String user = null;
		String password = null;
		String smsBaseUrl = null;
		try {
			user = parameterService.getParameterByCode("GU_USER_SMS").getPrmStringValue();
			password = parameterService.getParameterByCode("GU_PASSWORD_SMS").getPrmStringValue();
			Parametre urlParam = parameterService.getParameterByCode("GU_SMS_BASE_URL");
			smsBaseUrl = urlParam.getPrmStringValue();

			content = URLEncoder.encode(content, "UTF-8");

			String strParse = "";
			int data = 0;

			URL url = new URL(smsBaseUrl + "?user=" + user + "&password=" + password + "&content=" + content
					+ "&telephone=" + telephone);
			logger.info(url.toString());

			java.io.InputStream in = null;
			if (urlParam.getPrmValue() == 1) {
				try {
					proxyHost = parameterService.getParameterByCode("proxy_host").getPrmStringValue();
					proxyPort = parameterService.getParameterByCode("proxy_port").getPrmStringValue();
				} catch (Exception e) {
					logger.error("Erreur lors de la recuperation de proxy_host et proxy port");
				}
				Proxy proxy = null;
				if (proxyHost != null && proxyPort != null) {
					proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
					in = url.openConnection(proxy).getInputStream();
				}
			} else {
				in = url.openConnection().getInputStream();
			}
			BufferedInputStream bufIn = new BufferedInputStream(in);
			do {
				data = bufIn.read();
				if (data == -1)
					break;
				strParse = strParse + (char) data;
			} while (true);
			if (in != null) {
				in.close();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static void sendSMSV2(String telephone, String content) {
		String user = null;
		String password = null;
		String smsBaseUrl = null;
		CloseableHttpClient httpclient = null;
		OkHttpClient client = new OkHttpClient();

		ParameterService parameterService = (ParameterService) JNDIUtils
				.lookUpEJB(EJBRegistry.ParameterServiceBean);
		try {
			user = parameterService.getParameterByCode("GU_USER_SMS").getPrmStringValue();
			password = parameterService.getParameterByCode("GU_PASSWORD_SMS").getPrmStringValue();
			Parametre urlParam = parameterService.getParameterByCode("GU_SMS_BASE_URL");
			smsBaseUrl = urlParam.getPrmStringValue();

			content = URLEncoder.encode(content, "UTF-8");

			String url = smsBaseUrl + "?user=" + user + "&password=" + password + "&content=" + content + "&telephone="
					+ telephone;
			logger.info(url);
			MediaType mediaType = MediaType.parse("application/json");
			String data ="";
			RequestBody body = RequestBody.create(mediaType, data);

			Request request = new Request.Builder().addHeader("Accept", "application/json")
					.addHeader("Content-Type", "application/json")
					.addHeader("Authorization", "key=AIzaSyBZj0FI8Uam_wD31zdpq2D7GlDJP_4muxA").url(url)
					.post(body).build();

			ParameterService paramService = (ParameterService) JNDIUtils.lookUpEJB(EJBRegistry.ParameterServiceBean);
			if (urlParam.getPrmValue() == 1) {
				String proxyPort = null;
				String proxyHost = null;
				try {
					proxyHost = parameterService.getParameterByCode("proxy_host").getPrmStringValue();
					proxyPort = parameterService.getParameterByCode("proxy_port").getPrmStringValue();
				} catch (Exception e) {
					logger.info("###### Erreur recuperation proxy port et proxy host #####");
				}
				if (proxyPort != null && proxyHost != null) {
					logger.info("##### Using Proxy #####");
					java.net.Proxy proxy1 = new java.net.Proxy(java.net.Proxy.Type.HTTP,
							new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
					client.setProxy(proxy1);
				}
			}



			client.newCall(request).enqueue(new Callback() {

				@Override
				public void onFailure(Request arg0, IOException arg1) {
					logger.info("OnFailure");
					logger.error("", arg1);
				}

				@Override
				public void onResponse(Response arg0) throws IOException {

					if (!arg0.isSuccessful()) {
						throw new IOException("Unexpected code " + arg0);
					} else {
						logger.info("OnResponse");
						String repbody = arg0.body().string();
						Integer codeRep = arg0.code();
						logger.info("Response code: " + codeRep);
						logger.info("Response Body: " + repbody);
					}

				}

			});


		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (httpclient != null)
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error("", e);
				}
		}
	}


	public static String send(String destinataire, String contenu) {
		String myURL = null;
		try {
			myURL = "http://178.33.232.31:8080/webtosms/SendServlet?user=TOTALCLUB&password=Tit21zlla&telephone="
					+ URLEncoder.encode(destinataire, "UTF-8") + "&content=" + URLEncoder.encode(contenu, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("", e1);
			return "Erreur d'encodage";
		}
		logger.info("Requeted URL: " + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);

				int cp;
				while ((cp = bufferedReader.read()) != -1) {
					sb.append((char) cp);
				}
				bufferedReader.close();
			}
			if (in != null) {
				in.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL: " + myURL, e);
		}

		return sb.toString();
	}

}