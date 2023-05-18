package sn.intouch.gu.api.entities;

import java.util.HashMap;
import java.util.Map;

public class ServiceExchange {

	private String serviceCode;
	private String srvPaiementMode;
	private Double serviceRemise;
	private Long srvMontant;
	private Map<String, String> infosSup;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Double getServiceRemise() {
		return serviceRemise;
	}

	public void setServiceRemise(Double serviceRemise) {
		this.serviceRemise = serviceRemise;
	}

	public Long getsrvMontant() {
		return srvMontant;
	}

	public void setsrvMontant(Long srvMontant) {
		this.srvMontant = srvMontant;
	}

	public Map<String, String> getInfosSup() {
		return infosSup;
	}

	public void setInfosSup(Map<String, String> infosSup) {
		this.infosSup = infosSup;
	}

	public String getSrvPaiementMode() {
		return srvPaiementMode;
	}

	public void setSrvPaiementMode(String srvPaiementMode) {
		this.srvPaiementMode = srvPaiementMode;
	}

	public ServiceExchange(String serviceCode, String srvPm, Double serviceRemise, Long srvMontant, Map<String, String> infosSup) {
		super();
		this.serviceCode = serviceCode;
		this.serviceRemise = serviceRemise;
		this.srvPaiementMode = srvPm;
		this.srvMontant = srvMontant;
		this.infosSup = infosSup;

		infosSup = new HashMap<String, String>();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public ServiceExchange() {
		super();
	}

}
