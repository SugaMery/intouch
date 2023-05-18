package sn.intouch.gu.api.ejb.models;

import java.io.Serializable;
import java.util.List;

public class USSDOperationModel extends ExchangeAbstract implements Serializable {
	
	private List<USSD> listeUssd;
	private Integer nbEffectue;
	private Double montantEffectue;
	private Integer nbEchec;
	private Double montantEchec;
	private Integer nbAttente;
	private Double montantAttente;
	private Double montantTotalFrais;
	private String idbulk;
	public List<USSD> getListeUssd() {
		return listeUssd;
	}
	public void setListeUssd(List<USSD> listeUssd) {
		this.listeUssd = listeUssd;
	}
	public Integer getNbEffectue() {
		return nbEffectue;
	}
	public void setNbEffectue(Integer nbEffectue) {
		this.nbEffectue = nbEffectue;
	}
	public Double getMontantEffectue() {
		return montantEffectue;
	}
	public void setMontantEffectue(Double montantEffectue) {
		this.montantEffectue = montantEffectue;
	}
	public Integer getNbEchec() {
		return nbEchec;
	}
	public void setNbEchec(Integer nbEchec) {
		this.nbEchec = nbEchec;
	}
	public Double getMontantEchec() {
		return montantEchec;
	}
	public void setMontantEchec(Double montantEchec) {
		this.montantEchec = montantEchec;
	}
	
	public Integer getNbAttente() {
		return nbAttente;
	}
	public void setNbAttente(Integer nbAttente) {
		this.nbAttente = nbAttente;
	}
	public void setNbAttente(int i) {
		this.nbAttente = i;
	}
	public Double getMontantAttente() {
		return montantAttente;
	}
	public void setMontantAttente(Double montantAttente) {
		this.montantAttente = montantAttente;
	}
	public String getIdbulk() {
		return idbulk;
	}
	public void setIdbulk(String idbulk) {
		this.idbulk = idbulk;
	}
	public Double getMontantTotalFrais() {
		return montantTotalFrais;
	}
	public void setMontantTotalFrais(Double montantTotalFrais) {
		this.montantTotalFrais = montantTotalFrais;
	}
	
	

}
