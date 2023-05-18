package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pays_iso")
public class PaysISO implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code_iso")
	private String codeISO;
	@Column(name = "currency")
	private String currency;
	@Column(name = "services", columnDefinition = "TEXT")
	private String services;
	@Column(name = "numero_spoc")
	private String numeroSpoc;
	@Column(name = "tel_length")
	private Integer telLength;
	@Column( name = "supprime")
	private Boolean supprime = false;
	@Column(name = "country_name")
	private String countryName;
	@Column(name = "indicatif")
	private Integer indicatif;
	@Column(name = "numeric_currency")
	private String numericCurrency;
	private String spareOp1, spareOp2, spareOp3, spareOp4, spareOp5, spareOp6, spareOp7;

	public PaysISO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getCodeISO() {
		return codeISO;
	}

	public void setCodeISO(String codeISO) {
		this.codeISO = codeISO;
	}
	
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	public String getNumeroSpoc() {
		return numeroSpoc;
	}

	public void setNumeroSpoc(String numeroSpoc) {
		this.numeroSpoc = numeroSpoc;
	}

	public Integer getTelLength() {
		return telLength;
	}

	public void setTelLength(Integer telLength) {
		this.telLength = telLength;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
	public Integer getIndicatif() {
		return indicatif;
	}

	public void setIndicatif(Integer indicatif) {
		this.indicatif = indicatif;
	}

	public String getNumericCurrency() {
		return numericCurrency;
	}

	public void setNumericCurrency(String numericCurrency) {
		this.numericCurrency = numericCurrency;
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

	public String getSpareOp6() {
		return spareOp6;
	}

	public void setSpareOp6(String spareOp6) {
		this.spareOp6 = spareOp6;
	}

	public String getSpareOp7() {
		return spareOp7;
	}

	public void setSpareOp7(String spareOp7) {
		this.spareOp7 = spareOp7;
	}

	@Override
	public String toString() {
		return "PaysISO [id=" + id + ", codeISO=" + codeISO + ", currency=" + currency + ", services=" + services
				+ ", numeroSpoc=" + numeroSpoc + ", telLength=" + telLength + ", supprime=" + supprime
				+ ", countryName=" + countryName + ", indicatif=" + indicatif + ", numericCurrency=" + numericCurrency
				+ ", spareOp1=" + spareOp1 + ", spareOp2=" + spareOp2 + ", spareOp3=" + spareOp3 + ", spareOp4="
				+ spareOp4 + ", spareOp5=" + spareOp5 + ", spareOp6=" + spareOp6 + ", spareOp7=" + spareOp7 + "]";
	}
}