package sn.intouch.gu.api.config;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import com.google.gson.Gson;
import sn.intouch.gu.api.ejb.entities.Agent;
import sn.intouch.gu.api.ejb.entities.Compte;
import sn.intouch.gu.api.ejb.entities.Connexion;
import sn.intouch.gu.api.ejb.entities.FraisServiceAgence;
import sn.intouch.gu.api.ejb.entities.Network;
import sn.intouch.gu.api.ejb.entities.PDA;
import sn.intouch.gu.api.ejb.entities.Parametre;
import sn.intouch.gu.api.ejb.entities.SalePoint;
import sn.intouch.gu.api.ejb.entities.Servic1;
import sn.intouch.gu.api.ejb.services.AgentService;
import sn.intouch.gu.api.ejb.services.AuthentificationService;
import sn.intouch.gu.api.ejb.services.CompteService;
import sn.intouch.gu.api.ejb.services.CompteServiceBean;
import sn.intouch.gu.api.ejb.services.FraisServiceAgenceService;
import sn.intouch.gu.api.ejb.services.ParameterService;
import sn.intouch.gu.api.ejb.services.PdaService;
import sn.intouch.gu.api.ejb.services.PrimitiveService;
import sn.intouch.gu.api.ejb.services.SalePointService;
import sn.intouch.gu.api.ejb.utils.EJBRegistry;
import sn.intouch.gu.api.ejb.utils.JNDIUtils;
import sn.intouch.gu.api.ejb.utils.Utils;
import sn.intouch.gu.api.entities.TokenAuthModelWeb;
import sn.intouch.gu.api.entities.Utilisateur;
import sn.intouch.gu.api.utils.SMS;
import sn.intouch.gu.api.utils.Service;


public class Authentification  extends  ServerResource{

	private static Logger logger = Logger.getLogger(Authentification.class);
	PdaService pdaService = (PdaService) JNDIUtils.lookUpEJB(EJBRegistry.PdaServiceBean);
	SalePointService salePointService = (SalePointService) JNDIUtils.lookUpEJB(EJBRegistry.SalePointServiceBean);
	AgentService agentService = (AgentService) JNDIUtils.lookUpEJB(EJBRegistry.AgentServiceBean);
	AuthentificationService authService = (AuthentificationService) JNDIUtils
			.lookUpEJB(EJBRegistry.AuthentificationServiceBean);
	private ParameterService parameterService = (ParameterService) JNDIUtils
			.lookUpEJB(EJBRegistry.ParameterServiceBean);
	private static final String EXCEPTION_MESS = "Une exception s'est produite";
	private static final String ACTIF = "ACTIF";
	private static PrimitiveService primitiveService=(PrimitiveService) JNDIUtils
			.lookUpEJB(EJBRegistry.PrimitiveServiceBean);
	private static  FraisServiceAgenceService fraisService=(FraisServiceAgenceService) JNDIUtils
			.lookUpEJB(EJBRegistry.FraisServiceAgenceServiceBean);
	
	private static  CompteService compteService=(CompteService) JNDIUtils
			.lookUpEJB(EJBRegistry.CompteServiceBean);

	@Post("json")
	public String getToken(String jsonExchange) throws NoSuchAlgorithmException {
		Gson gson = new Gson();
		TokenAuthModelWeb tokenAuthModel = new TokenAuthModelWeb();
		logger.info("---------- STARTED AUTH EXCANGE REQUEST ------------");
		logger.info(jsonExchange);
		logger.info("----------- END AUTH EXCANGE REQUEST --------------");
		String activateAuthToken=null;
		
		Parametre param = parameterService.getParameterByCode("ACTIVE_AUTH_TOKEN_BULK");
		
		if(param!=null) {
			activateAuthToken=param.getPrmStringValue();
		}


			try {
				tokenAuthModel = gson.fromJson(jsonExchange, TokenAuthModelWeb.class);
			} catch (Exception e) {
				logger.error("", e);
				tokenAuthModel.setErrorCode("500");
				tokenAuthModel.setErrorMessage(EXCEPTION_MESS);
				return gson.toJson(tokenAuthModel);

			}
			Agent a = null;
			a=authService.getAgentByLogin(tokenAuthModel.getLoginAgent());
			if(a!=null){
				if (a.getStatut().equalsIgnoreCase(ACTIF)) {

					if(!a.getPassword().equals(tokenAuthModel.getPassword())){
						if(a.getCompteur()<2)
						{
							a.setCompteur(a.getCompteur()+1);
							authService.save(a);
							tokenAuthModel.setErrorCode("4031");
							tokenAuthModel.setErrorMessage("Mot de passe incorecte, veuillez réessayer"
									+ " (1/3) aprés 3 tentatives votre compte sera bloqué");
							return gson.toJson(tokenAuthModel);
						}
						else if(a.getCompteur()==2)
						{
							a.setCompteur(a.getCompteur()+1);
							authService.save(a);
							tokenAuthModel.setErrorCode("4032");
							tokenAuthModel.setErrorMessage("Mot de passe incorecte, veuillez réessayer"
									+ " (2/3) aprés 3 tentatives votre compte sera bloqué");
							return gson.toJson(tokenAuthModel);

						}
						else
						{
							a.setStatut("INACTIF");
							a.setCompteur(0L);
							authService.save(a);
							tokenAuthModel.setErrorCode("4033");
							tokenAuthModel.setErrorMessage("Mot de passe incorecte (3/3),votre compte vient "
									+ "d'être bloqué.Veuillez contacter votre administrateur");
							return gson.toJson(tokenAuthModel);


						}
					}else{

						//RECUPERATION INFO AGENCE TOKEN

						SalePoint salePoint =a.getSalepoint();

						if (salePoint.getStatut() != null) {
							if (salePoint.getStatut().equalsIgnoreCase(ACTIF)) {
								
								//Verification compte agence
								List<Compte> listeCompte=compteService.getComptesByCodeAgence(salePoint.getSalepoint_code());
                             
								if(listeCompte!=null && !listeCompte.isEmpty()){
									
									List<PDA> pdas = pdaService.findPDAByStation(salePoint.getSalepoint_code());
									PDA pda = Utils.getFirstActivatePda(pdas);
									List<FraisServiceAgence> liste=new ArrayList<FraisServiceAgence>();
									List<Service> services=new ArrayList<Service>();
									
									
									if (pda != null) {
										
										try {
											liste=fraisService.lister(salePoint.getSalepoint_code(), null,null);
											services = sn.intouch.gu.api.utils.Utils.fromListServic1ToListServices(liste);
										} catch (Exception e) {
											logger.error("", e);
										}
										tokenAuthModel.setCodePDA(pda.getCodePda());
										tokenAuthModel.setSalePointCode(salePoint.getSalepoint_code());
										tokenAuthModel.setSalePointName(salePoint.getSalepoint_nom());
										tokenAuthModel.setServices(services);
										String login = tokenAuthModel.getLoginAgent();
										String pass = tokenAuthModel.getPassword();
										String mac = tokenAuthModel.getAdresseMAC();
										String telephone = null;
										String token;
										Connexion connection = null;
										telephone = a.getAgtTelephone();
										connection = authService.getConnexionByAgent(a, mac, null);

										if (connection != null) {
											if (connection.isTokenValidated() || (activateAuthToken!=null && activateAuthToken.equals("0")) ) {
												tokenAuthModel.setErrorCode("200");
												tokenAuthModel.setErrorMessage("ABLETOCONNECT");
												Utilisateur utilisateur = null;
												utilisateur = fromAgentToUtilisateur(a);
												tokenAuthModel.setUtilisateur(utilisateur);
												a.setCompteur(0L);
												authService.save(a);
												
											} 
											else {
												/* Générer un token */
												connection = new Connexion(login, pass, mac);
												/*
												 * Enregistrer Connexion avec un token généré et l'envoyer par SMS
												 */
												token = sn.intouch.gu.api.utils.Utils.generate();
												connection.setToken(token);
												connection.setTokenValidated(false);
												logger.info("********* " + connection);
												authService.saveConnexion(connection);
												/* Envoi SMS à l'utilisateur */
												try {
													SMS.sendSMSV2("221" + telephone, token);
												} catch (Exception e) {
													logger.info("Problemes d'envoi du SMS");
													logger.error("", e);
												}
												tokenAuthModel.setErrorCode("200");
												tokenAuthModel.setErrorMessage("ASKTOKEN");

											}

										}else{
											
											
											if(activateAuthToken!=null && activateAuthToken.equals("0")) {
												setData(tokenAuthModel);
											}else {
												/* Générer un token */
												connection = new Connexion(login, pass, mac);
												/*
												 * Enregistrer Connexion avec un token généré et l'envoyer par SMS
												 */
												
												connection.setTokenValidated(false);
												logger.info("********* " + connection);
												token = sn.intouch.gu.api.utils.Utils.generate();
												connection.setToken(token);
												authService.saveConnexion(connection);
												/* Envoi SMS à l'utilisateur */
												try {
													SMS.sendSMSV2("221" + telephone, token);
												} catch (Exception e) {
													logger.info("Problemes d'envoi du SMS");
													logger.error("", e);
												}
												tokenAuthModel.setErrorCode("200");
												tokenAuthModel.setErrorMessage("ASKTOKEN");
											}
											
										}

									}else{
										tokenAuthModel.setErrorCode("4094");
										tokenAuthModel.setErrorMessage("Votre agence ne dispose pas de PDA actif. Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67");
									}
									
								}else{
									tokenAuthModel.setErrorCode("4055");
									tokenAuthModel.setErrorMessage("Votre agence ne dispose pas de compte. Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
								}
								
								
								 
							}else{
								tokenAuthModel.setErrorCode("4066");
								tokenAuthModel.setErrorMessage("Votre agence inactive. Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67");
							}


						}else{
							tokenAuthModel.setErrorCode("4066");
							tokenAuthModel.setErrorMessage("Votre agence inactive. Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67");
						}
					}
			}else{
				tokenAuthModel.setErrorCode("4078");
				tokenAuthModel.setErrorMessage("Votre compte est inactif. Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67");
			}
		}else{
			tokenAuthModel.setErrorCode("4089");
			tokenAuthModel.setErrorMessage("Nom d'utilisateur incorrect");
		}
	
			return gson.toJson(tokenAuthModel);
	}
	
	
	@Put("json")
	public String verifyToken(String jsonExchange) throws NoSuchAlgorithmException {
		Gson gson = new Gson();
		TokenAuthModelWeb tokenAuthModel = new TokenAuthModelWeb();
		logger.info("---------- STARTED AUTH EXCANGE REQUEST ------------");
		logger.info(jsonExchange);
		logger.info("----------- END AUTH EXCANGE REQUEST --------------");
		
			try {
				tokenAuthModel = gson.fromJson(jsonExchange, TokenAuthModelWeb.class);
			} catch (Exception e) {
				logger.error("", e);
				tokenAuthModel.setErrorCode("500");
				tokenAuthModel.setErrorMessage(EXCEPTION_MESS);
				return gson.toJson(tokenAuthModel);

			}
			Agent a = null;
			a=authService.getAgentByLogin(tokenAuthModel.getLoginAgent());
			if(a!=null){
				if (a.getStatut().equalsIgnoreCase(ACTIF)) {

					if(!a.getPassword().equals(tokenAuthModel.getPassword())){
						if(a.getCompteur()<2)
						{
							a.setCompteur(a.getCompteur()+1);
							authService.save(a);
							tokenAuthModel.setErrorCode("4031");
							tokenAuthModel.setErrorMessage("Login ou Mot de passe incorecte, veuillez réessayer"
									+ " (1/3) aprés 3 tentatives votre compte sera bloqué");
							return gson.toJson(tokenAuthModel);
						}
						else if(a.getCompteur()==2)
						{
							a.setCompteur(a.getCompteur()+1);
							authService.save(a);
							tokenAuthModel.setErrorCode("4032");
							tokenAuthModel.setErrorMessage("Login ou Mot de passe incorecte, veuillez réessayer"
									+ " (2/3) aprés 3 tentatives votre compte sera bloqué");
							return gson.toJson(tokenAuthModel);

						}
						else
						{
							a.setStatut("INACTIF");
							a.setCompteur(0L);
							authService.save(a);
							tokenAuthModel.setErrorCode("4033");
							tokenAuthModel.setErrorMessage("Login ou  Mot de passe incorecte (3/3),votre compte vient "
									+ "d'être bloqué.Veuillez contacter votre administrateur");
							return gson.toJson(tokenAuthModel);


						}
					}else{

						//RECUPERATION INFO AGENCE TOKEN

						SalePoint salePoint =a.getSalepoint();

						if (salePoint.getStatut() != null) {
							if (salePoint.getStatut().equalsIgnoreCase(ACTIF)) {
								
								//Verification compte agence
								List<Compte> listeCompte=compteService.getComptesByCodeAgence(salePoint.getSalepoint_code());
                             
								if(listeCompte!=null && !listeCompte.isEmpty()){
									List<PDA> pdas = pdaService.findPDAByStation(salePoint.getSalepoint_code());
									PDA pda = Utils.getFirstActivatePda(pdas);
									List<FraisServiceAgence> liste=new ArrayList<FraisServiceAgence>();
									List<Service> services=new ArrayList<Service>();
								
									if (pda != null) {
										
										try {
											liste=fraisService.lister(salePoint.getSalepoint_code(), null,null);
											services = sn.intouch.gu.api.utils.Utils.fromListServic1ToListServices(liste);
										} catch (Exception e) {
											logger.error("", e);
										}
										tokenAuthModel.setCodePDA(pda.getCodePda());
										tokenAuthModel.setSalePointCode(salePoint.getSalepoint_code());
										tokenAuthModel.setSalePointName(salePoint.getSalepoint_nom());
										tokenAuthModel.setServices(services);
										String login = tokenAuthModel.getLoginAgent();
										String pass = tokenAuthModel.getPassword();
										String mac = tokenAuthModel.getAdresseMAC();
										String token = tokenAuthModel.getTokenSms();
										String telephone = null;
										Connexion connection = null;
										telephone = a.getAgtTelephone();
										connection = authService.getConnexionByAgent(a, mac, token);

										if (connection != null) {
											connection.setToken(null); //
											connection.setTokenValidated(true);
											authService.saveConnexion(connection);
											logger.info("*********** " + connection);
											a.setCompteur(0L);
											authService.save(a);
											tokenAuthModel.setErrorCode("200");
											tokenAuthModel.setErrorMessage("IDENTIFIED");
											Utilisateur utilisateur = null;
											utilisateur = fromAgentToUtilisateur(a);
											tokenAuthModel.setUtilisateur(utilisateur);
											

										}else{
											tokenAuthModel.setErrorCode("404");
											tokenAuthModel.setErrorMessage("UNIDENTIFIED");
										}

									}else{
										tokenAuthModel.setErrorCode("4094");
										tokenAuthModel.setErrorMessage("Votre agence ne dispose pas de PDA actif.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
									}
									
								}else{
									tokenAuthModel.setErrorCode("4055");
									tokenAuthModel.setErrorMessage("Votre agence ne dispose pas de compte.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
								
								}

								 
							}else{
								tokenAuthModel.setErrorCode("4066");
								tokenAuthModel.setErrorMessage("Votre agence est inactive.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
							}


						}else{
							tokenAuthModel.setErrorCode("4066");
							tokenAuthModel.setErrorMessage("Votre agence est inactive.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
						}
					}
			}else{
				tokenAuthModel.setErrorCode("4078");
				tokenAuthModel.setErrorMessage("Votre compte est inactif.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
			}
		}else{
			tokenAuthModel.setErrorCode("4089");
			tokenAuthModel.setErrorMessage("Nom d'utilisateur incorrect");
		}
	return gson.toJson(tokenAuthModel);
	}
	
	
	public void setData(TokenAuthModelWeb tokenAuthModel) {
			
			Agent a = null;
			a=authService.getAgentByLogin(tokenAuthModel.getLoginAgent());
			if(a!=null){
				if (a.getStatut().equalsIgnoreCase(ACTIF)) {

					if(!a.getPassword().equals(tokenAuthModel.getPassword())){
						if(a.getCompteur()<2)
						{
							a.setCompteur(a.getCompteur()+1);
							authService.save(a);
							tokenAuthModel.setErrorCode("4031");
							tokenAuthModel.setErrorMessage("Login ou Mot de passe incorecte, veuillez réessayer"
									+ " (1/3) aprés 3 tentatives votre compte sera bloqué");
						}
						else if(a.getCompteur()==2)
						{
							a.setCompteur(a.getCompteur()+1);
							authService.save(a);
							tokenAuthModel.setErrorCode("4032");
							tokenAuthModel.setErrorMessage("Login ou Mot de passe incorecte, veuillez réessayer"
									+ " (2/3) aprés 3 tentatives votre compte sera bloqué");

						}
						else
						{
							a.setStatut("INACTIF");
							a.setCompteur(0L);
							authService.save(a);
							tokenAuthModel.setErrorCode("4033");
							tokenAuthModel.setErrorMessage("Login ou Mot de passe incorecte (3/3),votre compte vient "
									+ "d'être bloqué.Veuillez contacter votre administrateur");


						}
					}else{

						//RECUPERATION INFO AGENCE TOKEN

						SalePoint salePoint =a.getSalepoint();

						if (salePoint.getStatut() != null) {
							if (salePoint.getStatut().equalsIgnoreCase(ACTIF)) {
								
								//Verification compte agence
								List<Compte> listeCompte=compteService.getComptesByCodeAgence(salePoint.getSalepoint_code());
                             
								if(listeCompte!=null && !listeCompte.isEmpty()){
									List<PDA> pdas = pdaService.findPDAByStation(salePoint.getSalepoint_code());
									PDA pda = Utils.getFirstActivatePda(pdas);
									List<FraisServiceAgence> liste=new ArrayList<FraisServiceAgence>();
									List<Service> services=new ArrayList<Service>();
									List<Agent> validateurs=new ArrayList<Agent>();
									List<Agent> enregistreurs=new ArrayList<Agent>();

								
									if (pda != null) {
										
										try {
											liste=fraisService.lister(salePoint.getSalepoint_code(), null,null);
											services = sn.intouch.gu.api.utils.Utils.fromListServic1ToListServices(liste);
										} catch (Exception e) {
											logger.error("", e);
										}
										
										try {
											validateurs=agentService.getAgentByFonction(salePoint.getSalepoint_code(), "VALIDATEUR_BULK");
											enregistreurs=agentService.getAgentByFonction(salePoint.getSalepoint_code(), "ENREGISTREUR_BULK");

										} catch (Exception e) {
											// TODO: handle exception
										}
										
										tokenAuthModel.setCodePDA(pda.getCodePda());
										tokenAuthModel.setSalePointCode(salePoint.getSalepoint_code());
										tokenAuthModel.setSalePointName(salePoint.getSalepoint_nom());
										tokenAuthModel.setServices(services);
										tokenAuthModel.setErrorCode("200");
										tokenAuthModel.setErrorMessage("ABLETOCONNECT");
										Utilisateur utilisateur = null;
										utilisateur = fromAgentToUtilisateur(a);
										tokenAuthModel.setUtilisateur(utilisateur);
										if(validateurs!=null && !validateurs.isEmpty()) {
											List<Utilisateur> valid=fromAgentToListUtilisateur(validateurs);
											tokenAuthModel.setAgentsvalidateur(valid);
										}
										
										if(enregistreurs!=null && !enregistreurs.isEmpty()) {
											List<Utilisateur> eng=fromAgentToListUtilisateur(enregistreurs);
											tokenAuthModel.setAgentsenregistreur(eng);
										}

										

									}else{
										tokenAuthModel.setErrorCode("4094");
										tokenAuthModel.setErrorMessage("Votre agence ne dispose pas de PDA actif.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
									}
									
								}else{
									tokenAuthModel.setErrorCode("4055");
									tokenAuthModel.setErrorMessage("Votre agence ne dispose pas de compte.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
								
								}

								 
							}else{
								tokenAuthModel.setErrorCode("4066");
								tokenAuthModel.setErrorMessage("Votre agence est inactive.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
							}


						}else{
							tokenAuthModel.setErrorCode("4066");
							tokenAuthModel.setErrorMessage("Votre agence est inactive.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
						}
					}
			}else{
				tokenAuthModel.setErrorCode("4078");
				tokenAuthModel.setErrorMessage("Votre compte est inactif.Veuillez contacter le spoc au (+221) 33 860 64 44 / (+221) 77 700 67 67.");
			}
		}else{
			tokenAuthModel.setErrorCode("4089");
			tokenAuthModel.setErrorMessage("Nom d'utilisateur incorrect");
		}
	}
	public static Utilisateur fromAgentToUtilisateur(Agent agent) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(agent.getAgtLogin());
		utilisateur.setNom(agent.getAgtNom());
		utilisateur.setPassword(agent.getAgtPassword());
		utilisateur.setProfil(agent.getFonction().getCode());
		utilisateur.setPrenom(agent.getAgtPrenom());
		utilisateur.setPosition(agent.getType());
		utilisateur.setTelephone(agent.getAgtTelephone());
		utilisateur.setUserHasConnected(agent.getAgtHasConnect());
		utilisateur.setMail(agent.getMail());
		utilisateur.setPoste(agent.getPoste());
		utilisateur.setUserHasConnected(agent.getAgtHasConnect());
		utilisateur.setLangue(agent.getLangue());
		utilisateur.setWelcomeBookmark(agent.getFonction().getWelcomeBookmark());
		String currency="";
		if(agent.getNetworkgroup_id()!=null) {
			if(agent.getNetworkgroup_id().getPaysISO()!=null) {
				currency=agent.getNetworkgroup_id().getPaysISO().getNumericCurrency();
			}
		}
		
		utilisateur.setCurrency(currency);
		return utilisateur;
	}
	
	
	public static List<Utilisateur> fromAgentToListUtilisateur(List<Agent> agents) {
		
		List<Utilisateur> liste=new ArrayList<>();
		for(Agent a:agents) {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setLogin(a.getAgtLogin());
			utilisateur.setNom(a.getAgtNom());
			utilisateur.setMail(a.getMail());
			liste.add(utilisateur);

		}
		
		
		return liste;
	}
}