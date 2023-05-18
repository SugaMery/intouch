package sn.intouch.gu.api.entities;

public class GetFraisExchange extends ExchangeAbstract{
	private Double montant;
	private Double frais;
	private String countryCode;
	

	public GetFraisExchange() {
		super();
	}


	public Double getMontant() {
		return montant;
	}


	public void setMontant(Double montant) {
		this.montant = montant;
	}


	public Double getFrais() {
		return frais;
	}


	public void setFrais(Double frais) {
		this.frais = frais;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
