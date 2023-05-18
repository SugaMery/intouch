package sn.intouch.gu.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.FraisServiceAgence;
import sn.intouch.gu.api.ejb.entities.Parametre;
import sn.intouch.gu.api.ejb.entities.SalePoint;
import sn.intouch.gu.api.ejb.entities.Servic1;
import sn.intouch.gu.api.ejb.services.AgentService;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.services.PdaService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;

/**
 * Utilitaires generaux
 */
public class Utils {
	public static Logger log = Logger.getLogger(Utils.class);
	public static AgentService agentService = (AgentService) JNDIUtils.lookUpEJB(EJBRegistry.AgentServiceBean);
	public static PdaService pdaService = (PdaService) JNDIUtils.lookUpEJB(EJBRegistry.PdaServiceBean);

	// public static final String codePays = "221";
	// public static String wizLogin = "intouch",
	// wizPass="3D1vuwM36yegM7KvVsU6";
	// public static String wizURL = "https://test-system.wizall.com";

	public static Date getEnDateTime(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = formatter.parse(str);
			return date;

		} catch (ParseException e) {

			return null;

		}
	}

	public static String getEnDate(Date date) {
		String d = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return d;
	}

	private static String chars = "1234567890";
	private static int charLength = chars.length();

	public static String generateString(int length) {
		StringBuilder pass = new StringBuilder(charLength);
		for (int x = 0; x < length; x++) {
			int i = (int) (Math.random() * charLength);
			pass.append(chars.charAt(i));
		}
		return pass.toString();
	}

	public static String getCommonProperty(String property) {
		Properties props = new Properties();
		try {
			props.load(Utils.class.getResourceAsStream("/common.properties"));
		} catch (IOException e) {
			return null;
		}
		return props.getProperty(property);
	}

	public static String getSettingsProperty(String property) {
		InputStream settingsStream = Utils.class.getClassLoader().getResourceAsStream("settings.properties");

		Properties props = new Properties();
		try {
			props.load(settingsStream);
		} catch (IOException e) {
			return null;
		}
		return props.getProperty(property);
	}

	public static Date getDateFromOmId(String omId) {
		if (omId != null) {
			String strs[] = omId.split(".");

			// Annexe
			String year = omId.substring(2, 4);
			String month = omId.substring(4, 6);
			String day = omId.substring(6, 8);
			String hour = omId.substring(9, 11);
			String minute = omId.substring(11, 13);

			SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

			try {
				Date date = formatter.parse(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00");
				return date;

			} catch (ParseException e) {

				return null;

			}
		} else
			return null;
	}

	public static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException exe) {
			exe.printStackTrace();
		} catch (KeyManagementException exep) {
			exep.printStackTrace();
		}
	}

	/**
	 * Test si un String est null ou vide
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static final boolean equalsWithNulls(Object a, Object b) {
		if (a == b)
			return true;
		if ((a == null) || (b == null))
			return false;
		return a.equals(b);
	}

	public static Map<String, String> authentication(String login, String password, String partnerContext) {
		Map<String, String> map = new HashMap<>();
		Agent agent = null;
		SalePoint agence = null;

		if (login == null || password == null) {
			map.put("isRequestBad", "true");
			log.info("//**** Login ou mot de passe vide ****//");
		} else {
			agent = agentService.getAgentByCredentials(login, password);
			if (agent == null) {
				map.put("isRequestBad", "true");
				// isRequestBad = true;
				log.info("//**** Pas d agent ****//");
			} else {
				agence = agent.getSalepoint();
				if (agence != null) {
					if (partnerContext.equalsIgnoreCase(agence.getContexte())) {
						map.put("isContextCorrect", "true");
						// isContextCorrect = true;
					}
				} else {
					map.put("isRequestBad", "true");
					// isRequestBad = true;
					log.info("//**** Pas d agence ****//");
				}

			}
		}

		return map;

	}
	
	public static  boolean isContain(String source, String subItem){
		String pattern = "\\b"+subItem+"\\b";
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(source);
		return m.find();
	}
	public static String getEncodedPassword(String key) throws NoSuchAlgorithmException {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}

	
		public static String generate() {
			Random r = new Random(System.currentTimeMillis());
			Integer generated = r.nextInt(1000000);
			System.out.println("Token genere: " + generated);
			System.out.println("Size: " + String.valueOf(generated).length());
			String result = String.valueOf(generated);
			if (result.length() < 6)
				result = StringUtils.leftPad(result, 6, "0");
			return result;
		}
		
		public static Service fromServic1ToServices(FraisServiceAgence servic1) {
			Service service = new Service();
			service.setSrvCode(servic1.getService().getService_code());
			
			return service;
		}

		public static List<Service> fromListServic1ToListServices(List<FraisServiceAgence> servic1s) {
			List<Service> services = new ArrayList<Service>();
			for (FraisServiceAgence servic1 : servic1s) {
				services.add(fromServic1ToServices(servic1));
			}
			return services;
		}
		public static Gson gson = new Gson();

		static {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

				@Override
				public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
						throws JsonParseException {
					String date = json.getAsJsonPrimitive().getAsString();



					try {
						return new Date(Long.parseLong(date));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return new Date();
				}


			});

			builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
				public JsonElement serialize(Date date, Type typeOfId, JsonSerializationContext context) {


					return new JsonPrimitive(date.getTime());
				}

			});


			gson = builder.create();

		}
		
		public static void proxification(HttpGet hp, Parametre parametre) {
			ParameterService paramService = (ParameterService) JNDIUtils.lookUpEJB(EJBRegistry.ParameterServiceBean);
			if (parametre.getPrmValue() == 1) {
				String proxyPort = null;
				String proxyHost = null;
				try {
					proxyPort = paramService.getParameterByCode("PROXY_PORT_WL").getPrmStringValue();
					proxyHost = paramService.getParameterByCode("PROXY_HOST_WL").getPrmStringValue();
				} catch (Exception e) {
					log.info("###### Erreur recuperation proxy port et proxy host #####");
				}
				if (proxyPort != null && proxyHost != null) {
					log.info("##### Using Proxy #####");
					HttpHost proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
					RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
					hp.setConfig(config);
				}
			}
		}

}
