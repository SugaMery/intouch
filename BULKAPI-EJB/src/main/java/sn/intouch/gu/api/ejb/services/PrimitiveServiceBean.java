package sn.intouch.gu.api.ejb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import sn.intouch.gu.api.ejb.entities.FraisServiceAgence;
import sn.intouch.gu.api.ejb.entities.Operation;
import sn.intouch.gu.api.ejb.entities.OperationMois;
import sn.intouch.gu.api.ejb.entities.USSDOperation;
import sn.intouch.gu.api.ejb.entities.USSDOperationMois;
import sn.intouch.gu.api.ejb.models.ActiviteModel;
import sn.intouch.gu.api.ejb.models.USSD;
import sn.intouch.gu.api.ejb.models.USSDOperationModel;
import sn.intouch.gu.api.ejb.utils.FunctionArg;
import sn.intouch.gu.api.ejb.utils.Functions;

@Remote
@Stateless
public class PrimitiveServiceBean  implements PrimitiveService{

	@EJB private FraisServiceAgenceService fraisService;
	@EJB private USSDOperationService ussdOperationSerice;
	@EJB private OperationService operationService;

	private static Logger log = Logger.getLogger(PrimitiveServiceBean.class);


	@Override
	public HashMap<String, Object> getFraisByServiceAndAmount(String codeService, String codeAgence, Double montant) {
		HashMap<String,Object> result = null;
		Double montantTrans=null;

		try {
			result = Functions.verifyFunctionArg(new FunctionArg("paymentAmount", String.valueOf(montant), 0.0, ">", "Double"));
			if(result != null && !result.get("MESSAGE").equals("OK"))  return result; else montant = (Double) result.get("OBJECT");

			FraisServiceAgence frais=fraisService.filterfrais(codeAgence, codeService);

			if(frais == null){
				result.put("MESSAGE", "FRAIS NOT FOUND");
				result.put("CODE", 404);
				return result;
			}else{
				Double percent=frais.getMontant();
				if(frais.getTypeFrais().equalsIgnoreCase("Pourcentage")){

					montantTrans= montant*percent/100;
				}else{
					montantTrans=percent;
				}

				result = new HashMap<String, Object>();
				result.put("MESSAGE", "OK");
				result.put("OBJECT", montantTrans);
				result.put("CODE", 202);
			}

		} catch (Exception e) {
			result.put("MESSAGE", "ERREUR INTERNE LORS DE LA RECUPERATION DU FRAIS");
			result.put("CODE", 500);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> getUssdBulk(String idbulk) {
		HashMap<String,Object> result =  new HashMap<String,Object>();

		try {
			List<USSDOperation> listeSucces=new ArrayList<>();

			try {
				listeSucces=ussdOperationSerice.getSuccessUssd(idbulk);
			} catch (Exception e) {
				result.put("MESSAGE", "ERREUR INTERNE LORS DE LA RECUPERATION DES OPERATION SUCCESS");
				result.put("CODE", 500);
				log.error("", e);
				return result;
			}
			List<USSDOperation> listeFailde=new ArrayList<>();
			try {
				listeFailde=ussdOperationSerice.getFaildeUssd(idbulk);
			} catch (Exception e) {
				result.put("MESSAGE", "ERREUR INTERNE LORS DE LA RECUPERATION DES OPERATION ECHEC");
				result.put("CODE", 500);
				log.error("", e);
				return result;
			}
			List<USSDOperation> listeEncours=new ArrayList<>();
			try {
				listeEncours=ussdOperationSerice.getEncoursUssd(idbulk);
			} catch (Exception e) {
				result.put("MESSAGE", "ERREUR INTERNE LORS DE LA RECUPERATION DES OPERATION EN COURS");
				result.put("CODE", 500);
				log.error("", e);
				return result;
			}

			List<USSD> finalList=new ArrayList<USSD>();
			Double montantEncours=0.0;
			Double montant=0.0;
			Double montantFaild=0.0;
			if(listeSucces!=null && !listeSucces.isEmpty()){

				for(USSDOperation u:listeSucces){
					USSD ssd=new USSD();
					ssd.setDestinataire(u.getDestinataire());
					ssd.setMontant(u.getMontant());
					ssd.setCodeService(u.getCodeService());
					ssd.setTagUssd(u.getTag());
					ssd.setTagBulk("envoye");
					montant+=u.getMontant();
					finalList.add(ssd);
				}

			}

			if(listeFailde!=null && !listeFailde.isEmpty()){

				for(USSDOperation u:listeFailde){
					USSD ssd=new USSD();
					ssd.setDestinataire(u.getDestinataire());
					ssd.setMontant(u.getMontant());
					ssd.setCodeService(u.getCodeService());
					ssd.setTagUssd(u.getTag());
					ssd.setTagBulk("echec");
					montantFaild+=u.getMontant();
					finalList.add(ssd);
				}

			}

			if(listeEncours!=null && !listeEncours.isEmpty()){

				for(USSDOperation u:listeEncours){
					USSD ssd=new USSD();
					ssd.setDestinataire(u.getDestinataire());
					ssd.setMontant(u.getMontant());
					ssd.setCodeService(u.getCodeService());
					ssd.setTagUssd(u.getTag());
					ssd.setTagBulk("attente");
					montantEncours+=u.getMontant();
					finalList.add(ssd);
				}

			}

			USSDOperationModel ussModel=new USSDOperationModel();
			ussModel.setListeUssd(finalList);
			ussModel.setMontantAttente(montantEncours);
			ussModel.setNbAttente(listeEncours.size());
			ussModel.setMontantEchec(montantFaild);
			ussModel.setNbEchec(listeFailde.size());
			ussModel.setMontantEffectue(montant);
			ussModel.setNbEffectue(listeSucces.size());

			result.put("MESSAGE", "SUCCESS");
			result.put("CODE", 202);
			result.put("OBJECT", ussModel);
			return result;

		} catch (Exception e) {
			result.put("MESSAGE", "ERREUR INTERNE LORS DU TRIATEMENT");
			result.put("CODE", 500);
			log.error("", e);
			return result;
		}
	}

	@Override
	public HashMap<String, Object> getOperation(String codeAgence, String dateDeb, String dateFin) {
		HashMap<String,Object> result =  new HashMap<String,Object>();
		Long dateDebL;
		Long dateFinL;
		List<Operation> listeOp=new ArrayList<Operation>();
		List<ActiviteModel> listeActivite=new ArrayList<ActiviteModel>();

		try {
			result = Functions.verifyFunctionArg(new FunctionArg("paymentAmount", dateDeb, 0L, ">", "Long"));
			if(result != null && !result.get("MESSAGE").equals("OK"))  
				return result; 
			else
				dateDebL = (Long) result.get("OBJECT");
			result = Functions.verifyFunctionArg(new FunctionArg("paymentAmount", dateFin, 0L, ">", "Long"));
			if(result != null && !result.get("MESSAGE").equals("OK"))  
				return result; 
			else
			  dateFinL = (Long) result.get("OBJECT");
			Date dateDebut=new Date(dateDebL);
			Date dateFine =new Date(dateFinL);
			try {
				listeOp=operationService.getOperations(codeAgence, dateDebut, dateFine);

				if(listeOp!=null && !listeOp.isEmpty()){
					for(Operation op:listeOp){
						ActiviteModel activiteModel=new ActiviteModel();
						activiteModel.setDateOp(op.getDateOp());
						activiteModel.setMontant(op.getMontantOp());
						activiteModel.setOperateur(op.getBanque());
						activiteModel.setReferenceTransaction(op.getNumTransactionGu());

						if(op.getTypeOperationOp().getCodeTypeOp().equalsIgnoreCase("APPROVISIONNEMENT_DISTRIBUTEUR")){
							activiteModel.setTypeOperation("approvisionnement");
						}

						listeActivite.add(activiteModel);
					}

					result = new HashMap<String, Object>();
					result.put("MESSAGE", "OK");
					result.put("OBJECT", listeActivite);
					result.put("CODE", 202);

				}else{
					result.put("MESSAGE", "NO OPERATION");
					result.put("CODE", 500);	
				}
			} catch (Exception e) {
				result.put("MESSAGE", "SERVER ERROR");
				result.put("CODE", 500);	
				log.error("", e);
			}


		} catch (Exception e) {
			result.put("MESSAGE", "SERVER ERROR");
			result.put("CODE", 500);	
			log.error("", e);
		}
		return result;
	}

	@Override
	public HashMap<String, Object> getUssdBulkByTags(String idbulk,String codeAgence) {
		
		HashMap<String,Object> result =  new HashMap<String,Object>();

		try {
			List<USSDOperation> listeSucces=new ArrayList<>();

			try {
				listeSucces=ussdOperationSerice.getAllUssd(idbulk,codeAgence);
				List<USSD> finalList=new ArrayList<USSD>();
				Double montantAttente=0.0;
				Double montantSuccess=0.0;
				Double montantFaild=0.0;
				Double montantTotalFrais=0.0;
				Integer nbAttente=0;
				Integer nbFailed=0;
				Integer nbSucces=0;
				String bulk = null;
				
				for(USSDOperation u:listeSucces){
					USSD ssd=new USSD();
					ssd.setDestinataire(u.getDestinataire());
					ssd.setMontant(u.getMontant());
					ssd.setCodeService(u.getCodeService());
					ssd.setTagUssd(u.getTag());
					ssd.setToken(u.getToken());
					ssd.setDate(u.getDate());
					ssd.setFrais(u.getFrais());
					
					try {
						if(u.getSpareOp3()!=null){
							String  [] tab = u.getSpareOp3().split(";");
							
							String infoBenef=tab[1];
							String [] tabinfo=infoBenef.split("_");
							ssd.setPrenom(tabinfo[0]);
							ssd.setNom(tabinfo[1]);
							if(tabinfo.length == 3){
								ssd.setNumSurfichier(tabinfo[2]);
							}
							String [] tabB=tab[0].split("_");
							bulk=tabB[1];
						}
					} catch (Exception e) {
						log.error("", e);
					}
					
					
					
					
					if(u.getTag().equalsIgnoreCase("sended") || u.getTag().equalsIgnoreCase("initiated")){
						ssd.setTagBulk("attente");
						montantAttente+=u.getMontant();
						nbAttente+=1;
					}
					
					if(u.getTag().equalsIgnoreCase("finished")){
						
						if(u.getActionDone() != null && u.getActionDone().startsWith("Echec")){
							ssd.setTagBulk("echec");
							montantFaild+=u.getMontant();
							nbFailed+=1;
						}else{
							ssd.setTagBulk("envoye");
							montantSuccess+=u.getMontant();
							nbSucces+=1;
							montantTotalFrais+=u.getFrais();
						}
						
					}
					finalList.add(ssd);
				}
				
				
				USSDOperationModel ussModel=new USSDOperationModel();
				ussModel.setListeUssd(finalList);
				ussModel.setMontantAttente(montantAttente);
				ussModel.setNbAttente(nbAttente);
				ussModel.setMontantEchec(montantFaild);
				ussModel.setNbEchec(nbFailed);
				ussModel.setMontantEffectue(montantSuccess);
				ussModel.setNbEffectue(nbSucces);
				ussModel.setIdbulk(bulk);
				ussModel.setMontantTotalFrais(montantTotalFrais);

				result.put("MESSAGE", "SUCCESS");
				result.put("CODE", 202);
				result.put("OBJECT", ussModel);
				return result;
			} catch (Exception e) {
				result.put("MESSAGE", "ERREUR INTERNE LORS DE LA RECUPERATION DES OPERATION USSD");
				result.put("CODE", 500);
				log.error("", e);
				return result;
			}
			
			

		} catch (Exception e) {
			result.put("MESSAGE", "ERREUR INTERNE LORS DU TRIATEMENT");
			result.put("CODE", 500);
			log.error("", e);
			return result;
		}
	}

	@Override
	public HashMap<String, Object> getTransactionSuccess(String idbulk) {
		List<USSDOperation> liste=new ArrayList<USSDOperation>();
		List<USSD> listeTrans=new ArrayList<>();
		HashMap<String,Object> result =  new HashMap<String,Object>();

		
		try {
			liste=ussdOperationSerice.getSuccessUssd(idbulk);
			if(liste!=null && !liste.isEmpty()){
				for(USSDOperation u:liste){
					USSD ssd=new USSD();
					ssd.setDestinataire(u.getDestinataire());
					ssd.setMontant(u.getMontant());
					ssd.setCodeService(u.getCodeService());
					ssd.setTagUssd(u.getTag());
					ssd.setToken(u.getToken());
					
					try {
						if(u.getSpareOp3()!=null){
							String  [] tab = u.getSpareOp3().split("|");
							String infoBenef=tab[1];
							String [] tabinfo=infoBenef.split("_");
							ssd.setPrenom(tabinfo[0]);
							ssd.setNom(tabinfo[1]);
						}
					} catch (Exception e) {
						log.error("", e);
					}
					listeTrans.add(ssd);
				}
				
				result.put("MESSAGE", "SUCCESS");
				result.put("CODE", 202);
				result.put("OBJECT", listeTrans);
				return result;
			}else{
				result.put("MESSAGE", "LISTE VIDE OU NULL");
				result.put("CODE", 500);
				return result;
			}
		} catch (Exception e) {
			result.put("MESSAGE", "ERREUR INTERNE SERVER");
			result.put("CODE", 500);
			log.error("", e);
			return result;
		}
	}

	@Override
	public HashMap<String, Object> getNbFaildeUssd(String idbulk,String codeAgence) {
		HashMap<String,Object> result =  new HashMap<String,Object>();
		
		try {
			Long nb=ussdOperationSerice.getNumberFailed(idbulk,codeAgence);
			result.put("MESSAGE", "SUCCESS");
			result.put("CODE", 202);
			result.put("OBJECT", nb);
			return result;
		} catch (Exception e) {
			result.put("MESSAGE", "ERREUR INTERNE SERVER");
			result.put("CODE", 500);
			log.error("", e);
			return result;
		}
	}

	@Override
	public HashMap<String, Object> getUssdBulkByTagsMois(String idbulk, String codeAgence) {

		HashMap<String,Object> result =  new HashMap<String,Object>();

		try {
			List<USSDOperationMois> listeSucces=new ArrayList<>();

			try {
				listeSucces=ussdOperationSerice.getAllUssdMois(idbulk,codeAgence);
				List<USSD> finalList=new ArrayList<USSD>();
				Double montantAttente=0.0;
				Double montantSuccess=0.0;
				Double montantFaild=0.0;
				Double montantTotalFrais=0.0;
				Integer nbAttente=0;
				Integer nbFailed=0;
				Integer nbSucces=0;
				String bulk = null;
				
				for(USSDOperationMois u:listeSucces){
					USSD ssd=new USSD();
					ssd.setDestinataire(u.getDestinataire());
					ssd.setMontant(u.getMontant());
					ssd.setCodeService(u.getCodeService());
					ssd.setTagUssd(u.getTag());
					ssd.setToken(u.getToken());
					ssd.setDate(u.getDate());
					ssd.setFrais(u.getFrais());
					
					try {
						if(u.getSpareOp3()!=null){
							String  [] tab = u.getSpareOp3().split(";");
							
							String infoBenef=tab[1];
							String [] tabinfo=infoBenef.split("_");
							ssd.setPrenom(tabinfo[0]);
							ssd.setNom(tabinfo[1]);
							if(tabinfo.length == 3){
								ssd.setNumSurfichier(tabinfo[2]);
							}
							String [] tabB=tab[0].split("_");
							bulk=tabB[1];
						}
					} catch (Exception e) {
						log.error("", e);
					}
					
					
					
					
					if(u.getTag().equalsIgnoreCase("sended") || u.getTag().equalsIgnoreCase("initiated")){
						ssd.setTagBulk("attente");
						montantAttente+=u.getMontant();
						nbAttente+=1;
					}
					
					if(u.getTag().equalsIgnoreCase("finished")){
						
						if(u.getActionDone() != null && u.getActionDone().startsWith("Echec")){
							ssd.setTagBulk("echec");
							montantFaild+=u.getMontant();
							nbFailed+=1;
						}else{
							ssd.setTagBulk("envoye");
							montantSuccess+=u.getMontant();
							nbSucces+=1;
							montantTotalFrais+=u.getFrais();
						}
						
					}
					finalList.add(ssd);
				}
				
				
				USSDOperationModel ussModel=new USSDOperationModel();
				ussModel.setListeUssd(finalList);
				ussModel.setMontantAttente(montantAttente);
				ussModel.setNbAttente(nbAttente);
				ussModel.setMontantEchec(montantFaild);
				ussModel.setNbEchec(nbFailed);
				ussModel.setMontantEffectue(montantSuccess);
				ussModel.setNbEffectue(nbSucces);
				ussModel.setIdbulk(bulk);
				ussModel.setMontantTotalFrais(montantTotalFrais);

				result.put("MESSAGE", "SUCCESS");
				result.put("CODE", 202);
				result.put("OBJECT", ussModel);
				return result;
			} catch (Exception e) {
				result.put("MESSAGE", "ERREUR INTERNE LORS DE LA RECUPERATION DES OPERATION USSD");
				result.put("CODE", 500);
				log.error("", e);
				return result;
			}
			
			

		} catch (Exception e) {
			result.put("MESSAGE", "ERREUR INTERNE LORS DU TRIATEMENT");
			result.put("CODE", 500);
			log.error("", e);
			return result;
		}
	}

	@Override
	public HashMap<String, Object> getOperationMois(String codeAgence, String dateDeb, String dateFin) {
			HashMap<String,Object> result =  new HashMap<String,Object>();
			Long dateDebL;
			Long dateFinL;
			List<OperationMois> listeOp=new ArrayList<OperationMois>();
			List<ActiviteModel> listeActivite=new ArrayList<ActiviteModel>();

			try {
				result = Functions.verifyFunctionArg(new FunctionArg("paymentAmount", dateDeb, 0L, ">", "Long"));
				if(result != null && !result.get("MESSAGE").equals("OK"))  
					return result; 
				else
					dateDebL = (Long) result.get("OBJECT");
				result = Functions.verifyFunctionArg(new FunctionArg("paymentAmount", dateFin, 0L, ">", "Long"));
				if(result != null && !result.get("MESSAGE").equals("OK"))  
					return result; 
				else
				  dateFinL = (Long) result.get("OBJECT");
				Date dateDebut=new Date(dateDebL);
				Date dateFine =new Date(dateFinL);
				try {
					listeOp=operationService.getOperationsMois(codeAgence, dateDebut, dateFine);

					if(listeOp!=null && !listeOp.isEmpty()){
						for(OperationMois op:listeOp){
							ActiviteModel activiteModel=new ActiviteModel();
							activiteModel.setDateOp(op.getDateOp());
							activiteModel.setMontant(op.getMontantOp());
							activiteModel.setOperateur(op.getBanque());
							activiteModel.setReferenceTransaction(op.getNumTransactionGu());

							if(op.getTypeOperationOp().getCodeTypeOp().equalsIgnoreCase("APPROVISIONNEMENT_DISTRIBUTEUR")){
								activiteModel.setTypeOperation("approvisionnement");
							}

							listeActivite.add(activiteModel);
						}

						result = new HashMap<String, Object>();
						result.put("MESSAGE", "OK");
						result.put("OBJECT", listeActivite);
						result.put("CODE", 202);

					}else{
						result.put("MESSAGE", "NO OPERATION");
						result.put("CODE", 500);	
					}
				} catch (Exception e) {
					result.put("MESSAGE", "SERVER ERROR");
					result.put("CODE", 500);	
					log.error("", e);
				}


			} catch (Exception e) {
				result.put("MESSAGE", "SERVER ERROR");
				result.put("CODE", 500);	
				log.error("", e);
			}
			return result;
		}
	}


