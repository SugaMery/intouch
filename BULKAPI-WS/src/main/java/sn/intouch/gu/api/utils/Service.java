package sn.intouch.gu.api.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String srvCode;
	private int srvUnitPrice;
	private String srvLabel;
	private int srvPlus;
	private String srvType;
	private String srvPromo;
	private String srvPrivPromo;
	private int srvAppDiscount;
	private Double montantMin;
	private Double montantMax;
	private String spareOp1;
	private String spareOp2;
	private String spareOp3;
	private String spareOp4;
	private String spareOp5;
	private String serviceSubCategoryCode;
	private String serviceSubCategoryNom;
	// Remise en %
	private double srvDiscountRate, srvPrivDiscountRate;

	// Remise en forfait
	private double srvDiscountAmount;

	/**
	 * ajoutées pour la synchronisation des services
	 */
	private String codeFournisseur;
	private boolean caisse, itinerant, pompiste;

	public double getSrvPrivDiscountAmount() {
		return srvPrivDiscountAmount;
	}

	public void setSrvPrivDiscountAmount(double srvPrivDiscountAmount) {
		this.srvPrivDiscountAmount = srvPrivDiscountAmount;
	}

	public double getSrvPrivDiscountRate() {
		return srvPrivDiscountRate;
	}

	public void setSrvPrivDiscountRate(double srvPrivDiscountRate) {
		this.srvPrivDiscountRate = srvPrivDiscountRate;
	}

	public void setSrvChamps(Map<String, String> srvChamps) {
		this.srvChamps = srvChamps;
	}

	private double srvPrivDiscountAmount;
	private int srvDisplayLevel;
	private int montant;
	private String srvJson;

	public Map<String, String> getSrvChamps() {
		return srvChamps;
	}

	// Champs et valeurs du service
	private Map<String, String> srvChamps;

	public int getSrvUnitPrice() {
		return srvUnitPrice;
	}

	public void setSrvUnitPrice(int srvUnitPrice) {
		this.srvUnitPrice = srvUnitPrice;
	}

	public double getSrvDiscountAmount() {
		return srvDiscountAmount;
	}

	public void setSrvDiscountAmount(double srvDiscountAmount) {
		this.srvDiscountAmount = srvDiscountAmount;
	}

	public double getSrvDiscountRate() {
		return srvDiscountRate;
	}

	public void setSrvDiscountRate(double srvDiscountRate) {
		this.srvDiscountRate = srvDiscountRate;
	}

	public int getSrvDisplayLevel() {
		return srvDisplayLevel;
	}

	public void setSrvDisplayLevel(int srvDisplayLevel) {
		this.srvDisplayLevel = srvDisplayLevel;
	}

	public String getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}

	public String getSrvLabel() {
		return srvLabel;
	}

	public void setSrvLabel(String srvLabel) {
		this.srvLabel = srvLabel;
	}

	public Service() {
		super();
	}

	public String getSrvType() {
		return srvType;
	}

	public void setSrvType(String srvType) {
		this.srvType = srvType;
	}

	public Service(String codeService, String srvLabel, String srvType, Map srvChamps) {
		this.srvCode = codeService;
		this.srvLabel = srvLabel;
		this.srvType = srvType;
		this.srvChamps = srvChamps;
	}

	public List<String> fromMaptoStrings() {
		List<String> strings = new ArrayList<String>();
		for (Object key : srvChamps.keySet()) {
			strings.add((String) key);
		}
		return strings;
	}

	public Service(String codeService, String srvLabel, String srvType, String srvPromo, double srvDiscountAmount,
			double srvDiscountRate) {
		srvCode = codeService;
		this.srvLabel = srvLabel;
		this.srvType = srvType;
		this.srvPromo = srvPromo;
		this.srvDiscountAmount = srvDiscountAmount;
		this.srvDiscountRate = srvDiscountRate;
		this.srvChamps = new HashMap();

	}

	public Service copy() {

		Service service = new Service();
		service.setSrvCode(srvCode);
		service.setSrvLabel(srvLabel);
		service.setMontant(montant);
		service.setSrvDiscountRate(srvDiscountRate);
		service.setSrvPromo(srvPromo);
		service.setSrvDiscountAmount(srvDiscountAmount);
		service.setSrvChamps(srvChamps);
		service.setSrvJson(srvJson);
		service.setSrvAppDiscount(srvAppDiscount);
		service.setSrvDisplayLevel(srvDisplayLevel);
		service.setSrvPlus(srvPlus);
		return service;
	}

	public String getSrvPromo() {
		return srvPromo;
	}

	public void setSrvPromo(String srvPromo) {
		this.srvPromo = srvPromo;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getSrvAppDiscount() {
		return srvAppDiscount;
	}

	public void setSrvAppDiscount(int srvAppDiscount) {
		this.srvAppDiscount = srvAppDiscount;
	}

	public int getSrvPlus() {
		return srvPlus;
	}

	public void setSrvPlus(int srvPlus) {
		this.srvPlus = srvPlus;
	}

	public String getSrvJson() {
		return srvJson;
	}

	public void setSrvJson(String srvJson) {
		this.srvJson = srvJson;
	}

	public String getSrvPrivPromo() {
		return srvPrivPromo;
	}

	public void setSrvPrivPromo(String srvPrivPromo) {
		this.srvPrivPromo = srvPrivPromo;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	public boolean isCaisse() {
		return caisse;
	}

	public void setCaisse(boolean caisse) {
		this.caisse = caisse;
	}

	public boolean isItinerant() {
		return itinerant;
	}

	public void setItinerant(boolean itinerant) {
		this.itinerant = itinerant;
	}

	public boolean isPompiste() {
		return pompiste;
	}

	public void setPompiste(boolean pompiste) {
		this.pompiste = pompiste;
	}

	public Double getMontantMin() {
		return montantMin;
	}

	public void setMontantMin(Double montantMin) {
		this.montantMin = montantMin;
	}

	public Double getMontantMax() {
		return montantMax;
	}

	public void setMontantMax(Double montantMax) {
		this.montantMax = montantMax;
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

	public String getServiceSubCategoryCode() {
		return serviceSubCategoryCode;
	}

	public void setServiceSubCategoryCode(String serviceSubCategoryCode) {
		this.serviceSubCategoryCode = serviceSubCategoryCode;
	}

	public String getServiceSubCategoryNom() {
		return serviceSubCategoryNom;
	}

	public void setServiceSubCategoryNom(String serviceSubCategoryNom) {
		this.serviceSubCategoryNom = serviceSubCategoryNom;
	}

}
