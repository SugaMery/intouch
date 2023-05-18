package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ussd_operations30j")
public class USSDOperationMois implements Serializable {
	private Long id;
	private Double montant;
	private String destinataire;
	private String token;
	private String operationType;
	private String tag;
	private String codePDA;
	private String codeSalePoint;
	private String codeNetwork;
	private String codeService;
	private String loginAgent;
	private String paymentMode;
	private String idFromPartenaire;
	private Double commission;
	private Double frais;
	private String codeProxy;
	private String categorie;
	private String userDoneAction;
	private String actionDone;
	private String network_groupe_code;
	private String callBackURL;
	private String partnerDistTransactionId;
	private String spareOp1, spareOp2, spareOp3, spareOp4, spareOp5;
	private String statutPartenaire=null;
	private Date sentDate;
	private String sms;
	private Date date;
	private Date dateTraitement;
	private String baseCalculCommission;
	private String latitude;
	private String longitude;

	public USSDOperationMois() {
		super();
		// TODO Auto-generated constructor stub
	}

	public USSDOperationMois(Double montant, String destinataire, String token, String operationType) {
		this.montant = montant;
		this.destinataire = destinataire;
		this.token = token;
		this.operationType = operationType;
	}
	@Column(name = "actionDone")
	public String getActionDone() {
		return actionDone;
	}

	public void setActionDone(String actionDone) {
		this.actionDone = actionDone;
	}

	@Column(name = "network_groupe_code")
	public String getNetwork_groupe_code() {
		return network_groupe_code;
	}

	public void setNetwork_groupe_code(String network_groupe_code) {
		this.network_groupe_code = network_groupe_code;
	}
	
	@Column(name = "base_calcul_comm")
	public String getBaseCalculCommission() {
		return baseCalculCommission;
	}

	public void setBaseCalculCommission(String baseCalculCommission) {
		this.baseCalculCommission = baseCalculCommission;
	}

	@Column(name = "userDoneAction")
	public String getUserDoneAction() {
		return userDoneAction;
	}

	public void setUserDoneAction(String userDoneAction) {
		this.userDoneAction = userDoneAction;
	}

	@Column(name = "categorie")
    public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Column(name = "code_proxy")
	public String getCodeProxy() {
		return codeProxy;
	}

	public void setCodeProxy(String codeProxy) {
		this.codeProxy = codeProxy;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "montant")
	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	@Column(name = "destinataire")
	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	@Column(name = "token", unique = true)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "type")
	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	@Column(name = "tag")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "codePDA")
	public String getCodePDA() {
		return codePDA;
	}

	public void setCodePDA(String codePDA) {
		this.codePDA = codePDA;
	}

	@Column(name = "codeSalePoint")
	public String getCodeSalePoint() {
		return codeSalePoint;
	}

	public void setCodeSalePoint(String codeSalePoint) {
		this.codeSalePoint = codeSalePoint;
	}

	@Column(name = "loginAgent")
	public String getLoginAgent() {
		return loginAgent;
	}

	public void setLoginAgent(String loginAgent) {
		this.loginAgent = loginAgent;
	}

	@Column(name = "paymentMode")
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "numTransFromPartenaire")
	public String getIdFromPartenaire() {
		return idFromPartenaire;
	}

	public void setIdFromPartenaire(String idFromPartenaire) {
		this.idFromPartenaire = idFromPartenaire;
	}

	@Column(name = "commission")
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	@Column(name = "frais")
	public Double getFrais() {
		return frais;
	}

	public void setFrais(Double frais) {
		this.frais = frais;
	}

	@Column(name = "codeNetwork")
	public String getCodeNetwork() {
		return codeNetwork;
	}

	public void setCodeNetwork(String codeNetwork) {
		this.codeNetwork = codeNetwork;
	}
	@Column(name = "codeService")
	public String getCodeService() {
		return codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	
	@Column(name = "call_back_url")
	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	@Column(name = "partner_dist_transaction_id", unique = true)
	public String getPartnerDistTransactionId() {
		return partnerDistTransactionId;
	}

	public void setPartnerDistTransactionId(String partnerDistTransactionId) {
		this.partnerDistTransactionId = partnerDistTransactionId;
	}
	
	

	public String getSpareOp1() {
		return spareOp1;
	}

	public void setSpareOp1(String spareOp1) {
		this.spareOp1 = spareOp1;
	}

	public String getSpareOp2() {
		return spareOp2;
	}

	public void setSpareOp2(String spareOp2) {
		this.spareOp2 = spareOp2;
	}

	public String getSpareOp3() {
		return spareOp3;
	}

	public void setSpareOp3(String spareOp3) {
		this.spareOp3 = spareOp3;
	}

	public String getSpareOp4() {
		return spareOp4;
	}

	public void setSpareOp4(String spareOp4) {
		this.spareOp4 = spareOp4;
	}

	public String getSpareOp5() {
		return spareOp5;
	}

	public void setSpareOp5(String spareOp5) {
		this.spareOp5 = spareOp5;
	}

	@Column(name = "statut_partenaire")
	public String getStatutPartenaire() {
		return statutPartenaire;
	}

	public void setStatutPartenaire(String statutPartenaire) {
		this.statutPartenaire = statutPartenaire;
	}
	@Column(name = "sent_date")
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	@Column(name = "sms")
	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}
	@Column(name = "date_traitement")
	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	
}
