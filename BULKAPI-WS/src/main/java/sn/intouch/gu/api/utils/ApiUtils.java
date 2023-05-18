package sn.intouch.gu.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.PDA;
import sn.intouch.gu.api.ejb.entities.Parametre;
import sn.intouch.gu.api.ejb.entities.SalePoint;
import sn.intouch.gu.api.ejb.services.AgentService;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.services.PdaService;
import sn.intouch.gu.api.ejb.services.SalePointService;
import sn.intouch.gu.api.ejb.services.TokenAPIService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.ejb.utils.Utils;
import sn.intouch.gu.api.entities.AbstractRequest;
import sn.intouch.gu.api.entities.TransactionExchange;

public class ApiUtils {

	private static Logger log = Logger.getLogger(ApiUtils.class);
	static SalePointService salePointService = (SalePointService) JNDIUtils.lookUpEJB(EJBRegistry.SalePointServiceBean);
	static AgentService agentService = (AgentService) JNDIUtils.lookUpEJB(EJBRegistry.AgentServiceBean);
	static PdaService pdaService = (PdaService) JNDIUtils.lookUpEJB(EJBRegistry.PdaServiceBean);
	static TokenAPIService tokenService = (TokenAPIService) JNDIUtils.lookUpEJB(EJBRegistry.TokenAPIServiceBean);
	static ParameterService parametreService = (ParameterService) JNDIUtils.lookUpEJB(EJBRegistry.ParameterServiceBean);

	public static Map<String, String> testResquest(Map<String, ? extends AbstractRequest> map, String context) {
		AbstractRequest request = map.get("request");
		Map<String, String> mapStr = new HashMap<String, String>();
		mapStr.put("message", "Erreur de traitement de la requete");

		try {
			if (context == null || context.equals("")) {
				log.info("Le contexte du partenaire n'est pas renseigne");
				mapStr.put("message", "Le contexte du partenaire n'est pas renseigne");
				return mapStr;
			} else {
				String loginAgent = request.getLoginApi();
				String passwordAgent = request.getPasswordApi();

				if (loginAgent == null || passwordAgent == null) {
					log.info("Login ou mot de passe vide");
					mapStr.put("message", "Login ou mot de passe vide");
					return mapStr;

				} else {
					Agent agent = agentService.getAgentByCredentials(loginAgent, passwordAgent);
					if (agent == null) {
						log.info("L'agent n'existe pas");
						mapStr.put("message", "L'agent n'existe pas");
						return mapStr;
					} else {
						SalePoint agence = agent.getSalepoint();
						if (agence != null) {
							if (context.equalsIgnoreCase(agence.getContexte())) {

								String codePDA = request.getPartnerId();

								if (codePDA == null) {
									log.info("Le partnerId n'est pas renseigne");
									mapStr.put("message", "Le partnerId n'est pas renseigne");
									return mapStr;
								} else {
									PDA pda = pdaService.findPDAByCode(codePDA);
									/**
									 * Verifier si le PDA et l agent sont du
									 * partenaire
									 */
									if (pda == null) {
										log.info("Ce partneraire n'existe");
										mapStr.put("message", "Ce partneraire n'existe pas.");
										return mapStr;
									} else {
										if (pda.getCodeStation() != null) {
											if (pda.getCodeStation().equalsIgnoreCase(agence.getSalepoint_code())) {
													
												log.info("Verification OK");
												mapStr.put("message", "OK");
												mapStr.put("loginAgent", agent.getAgtLogin());
												mapStr.put("salepointCode", agence.getSalepoint_code());
												
												return mapStr;
												
											} else {
												log.info("Verifiez les informations saisies");
												mapStr.put("message", "Verifiez les informations saisies");
												return mapStr;
											}
										} else {
											log.info("Ce partnerId n'appartient à aucune agence");
											mapStr.put("message", "Ce partnerId n'appartient à aucune agence");
											return mapStr;
										}

									}

								}

							} else {
								log.info("Le contexte saisi n'appartient pas à cette agence");
								mapStr.put("message", "Le contexte saisi n'appartient pas à cette agence");
								return mapStr;
							}
						} else {
							log.info("L'agence n'existe pas");
							mapStr.put("message", "L'agence n'existe pas");
							return mapStr;
						}

					}
				}
			}
		
		} catch (Exception e) {
			log.error("", e);
		}

		return mapStr;

	}
	
	public static Map<String, String> testResquestmd5(Map<String, ? extends AbstractRequest> map, String context) {
		AbstractRequest request = map.get("request");
		Map<String, String> mapStr = new HashMap<String, String>();
		mapStr.put("message", "Erreur de traitement de la requete");

		try {
			if (context == null || context.equals("")) {
				log.info("Le contexte du partenaire n'est pas renseigné");
				mapStr.put("message", "Le contexte du partenaire n'est pas renseigné");
				return mapStr;
			} else {
				String loginAgent = request.getLoginApi();
				String passwordAgent = request.getPasswordApi();

				if (loginAgent == null || passwordAgent == null) {
					log.info("Login ou mot de passe vide");
					mapStr.put("message", "Login ou mot de passe vide");
					return mapStr;

				} else {
					String passwordAgentCrypted = Utils.getEncodedPassword(passwordAgent);
					Agent agent = agentService.getAgentByCredentials(loginAgent, passwordAgentCrypted);
					if (agent == null) {
						log.info("L'agent n'existe pas");
						mapStr.put("message", "L'agent n'existe pas");
						return mapStr;
					} else {
						SalePoint agence = agent.getSalepoint();
						if (agence != null) {
							if (context.equalsIgnoreCase(agence.getContexte())) {

								String codePDA = request.getPartnerId();

								if (codePDA == null) {
									log.info("Le partnerId n'est pas renseigné");
									mapStr.put("message", "Le partnerId n'est pas renseigné");
									return mapStr;
								} else {
									PDA pda = pdaService.findPDAByCode(codePDA);
									/**
									 * Verifier si le PDA et l agent sont du
									 * partenaire
									 */
									if (pda == null) {
										log.info("Ce partneraire n'existe");
										mapStr.put("message", "Ce partneraire n'existe pas.");
										return mapStr;
									} else {
										if (pda.getCodeStation() != null) {
											if (pda.getCodeStation().equalsIgnoreCase(agence.getSalepoint_code())) {
													
												log.info("Verification OK");
												mapStr.put("message", "OK");
												mapStr.put("loginAgent", agent.getAgtLogin());
												mapStr.put("salepointCode", agence.getSalepoint_code());
												
												return mapStr;
												
											} else {
												log.info("Verifiez les informations saisies");
												mapStr.put("message", "Verifiez les informations saisies");
												return mapStr;
											}
										} else {
											log.info("Ce partnerId n'appartient à aucune agence");
											mapStr.put("message", "Ce partnerId n'appartient à aucune agence");
											return mapStr;
										}

									}

								}

							} else {
								log.info("Le contexte saisi n'appartient pas à cette agence");
								mapStr.put("message", "Le contexte saisi n'appartient pas à cette agence");
								return mapStr;
							}
						} else {
							log.info("L'agence n'existe pas");
							mapStr.put("message", "L'agence n'existe pas");
							return mapStr;
						}

					}
				}
			}
		
		} catch (Exception e) {
			log.error("", e);
		}

		return mapStr;

	}
	
	public static Map<String, String> testResponse(TransactionExchange te) {
		Map<String, String> mapStr = new HashMap<String, String>();
		mapStr.put("statusCode", "500");
		mapStr.put("status", "FAILED");
		mapStr.put("message", "Erreur interne de serveur");

		try {
			if (te != null && te.getErrorCode() != null) {
				if (te.getErrorCode().equals("400")) {
					mapStr.put("statusCode", "400");
					mapStr.put("message", "Mavaise requete");
				} else if (te.getErrorCode().equals("403")) {
					mapStr.put("statusCode", "403");
					mapStr.put("message", "L'etat de votre compte ne vous permet pas de faire cette operation.");
				} else if (te.getErrorCode().equals("404")) {
					mapStr.put("statusCode", "404");
					mapStr.put("message", "Ressource non disponible");
				} else if (te.getErrorCode().equals("500")) {
					mapStr.put("statusCode", "500");
					mapStr.put("message", "Erreur interne de serveur");
				} else if (te.getErrorCode().equals("200")) {
					mapStr.put("statusCode", "200");
					mapStr.put("status", "SUCCESSFUL");
					mapStr.put("message", "SUCCESSFUL");
				} else if (te.getErrorCode().equals("202")) {
					mapStr.put("statusCode", "200");
					mapStr.put("status", "PENDING");
					mapStr.put("message", "Operation en cours de traitement");
				} else if (te.getErrorCode().equals("203")) {
					mapStr.put("statusCode", "203");
					mapStr.put("message", "Une operation similaire a ete envoyee il y a moins de 10 minutes");
				} else if (te.getErrorCode().equals("204")) {
					mapStr.put("statusCode", "204");
					mapStr.put("message", "Operation deja prise en compte. Veuillez patienter...");
				} else if (te.getErrorCode().equals("207")) {
					mapStr.put("statusCode", "207");
					mapStr.put("message", "DMA atteint");
				} else {
					mapStr.put("statusCode", te.getErrorCode());
				}
			}
			
		} catch (Exception e) {
			log.error("", e);
		}

		return mapStr;

	}
	
	public static Map<String, String> testUEResponse(TransactionExchange te) {
		Map<String, String> mapStr = new HashMap<String, String>();
		mapStr.put("statusCode", "500");
		mapStr.put("status", "FAILED");
		mapStr.put("message", "Erreur interne de serveur");

		try {
			if (te != null && te.getErrorCode() != null) {
				
				mapStr.put("statusCode", te.getErrorCode());
				if (te.getErrorMessage() != null){
					mapStr.put("message", te.getErrorMessage());				
				}				
				if (te.getErrorCode() != null && te.getErrorCode().equals("200")){
					mapStr.put("status", "SUCCESSFUL");
				}
				
			}
			
		} catch (Exception e) {
			log.error("", e);
		}

		return mapStr;

	}
	
	public static Map<String, String> checkResponse(TransactionExchange te) {
		Map<String, String> mapStr = new HashMap<String, String>();
		mapStr.put("statusCode", "500");
		mapStr.put("status", "FAILED");
		mapStr.put("message", "Erreur interne de serveur");

		try {
			if (te != null && te.getErrorCode() != null) {
				
				mapStr.put("statusCode", te.getErrorCode());
				if (te.getErrorMessage() != null){
					mapStr.put("message", te.getErrorMessage());				
				}				
				if (te.getErrorCode() != null && te.getErrorCode().equals("200")){
					mapStr.put("status", "SUCCESSFUL");
				}
				
			}
			
		} catch (Exception e) {
			log.error("", e);
		}

		return mapStr;

	}
	
	public static Boolean checkAmount (Float amount){
		Boolean isGood = true;
		
		//Verifier si le montant est autorise
		try {
			
			if (amount == null){
				return false;
			}
			
			Parametre paramMinMontant = parametreService.getParameterByCode("TOUCHAPI_MIN_MONTANT");
			Parametre paramMaxMontant = parametreService.getParameterByCode("TOUCHAPI_MAX_MONTANT");
			if (paramMinMontant != null && paramMaxMontant != null) {
				String minMontant = paramMinMontant.getPrmStringValue();
				String maxMontant = paramMaxMontant.getPrmStringValue();
				if (minMontant != null && maxMontant != null) {
					
					if (amount < Float.valueOf(minMontant) || amount > Float.valueOf(maxMontant)) {
						log.info("Montant demandé non autorisé");
						return false;
					}
					
				}
				
			}
		} catch (Exception e) {
			log.error("", e);
		}
		
		return isGood;
		
	}
	
	public static Boolean checkDoubleAmount(Double amount){
		Boolean isGood = true;
		
		//Verifier si le montant est autorise
		try {
			
			if (amount == null){
				return false;
			}
			
			Parametre paramMinMontant = parametreService.getParameterByCode("TOUCHAPI_MIN_MONTANT");
			Parametre paramMaxMontant = parametreService.getParameterByCode("TOUCHAPI_MAX_MONTANT");
			if (paramMinMontant != null && paramMaxMontant != null) {
				String minMontant = paramMinMontant.getPrmStringValue();
				String maxMontant = paramMaxMontant.getPrmStringValue();
				if (minMontant != null && maxMontant != null) {
					
					if (amount < Double.valueOf(minMontant) || amount > Double.valueOf(maxMontant)) {
						log.info("Montant demandé non autorisé");
						return false;
					}
					
				}
				
			}
		} catch (Exception e) {
			log.error("", e);
		}
		
		return isGood;
		
	}
	
	public static Boolean checkService(Double amount){
		Boolean isGood = true;
		
		//Verifier si le montant est autorise
		try {
			
			if (amount == null){
				return false;
			}
			
			Parametre paramMinMontant = parametreService.getParameterByCode("TOUCHAPI_MIN_MONTANT");
			Parametre paramMaxMontant = parametreService.getParameterByCode("TOUCHAPI_MAX_MONTANT");
			if (paramMinMontant != null && paramMaxMontant != null) {
				String minMontant = paramMinMontant.getPrmStringValue();
				String maxMontant = paramMaxMontant.getPrmStringValue();
				if (minMontant != null && maxMontant != null) {
					
					if (amount < Double.valueOf(minMontant) || amount > Double.valueOf(maxMontant)) {
						log.info("Montant demandé non autorisé");
						return false;
					}
					
				}
				
			}
		} catch (Exception e) {
			log.error("", e);
		}
		
		return isGood;
		
	}

}
