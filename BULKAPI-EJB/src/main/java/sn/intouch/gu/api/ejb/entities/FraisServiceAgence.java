package sn.intouch.gu.api.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FraisService
 *
 */
@Entity
@Table(name = "frais_service_agence")
public class FraisServiceAgence implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private SalePoint agence;
	private Servic service;
	private String typeFrais;
	private Double montant;
	private boolean supprime=false;

	public FraisServiceAgence() {
		super();
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

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER )
	@JoinColumn(name="agence")
	public SalePoint getAgence() {
		return agence;
	}

	public void setAgence(SalePoint agence) {
		this.agence = agence;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER )
	@JoinColumn(name="service")
	public Servic getService() {
		return service;
	}

	public void setService(Servic service) {
		this.service = service;
	}

	public String getTypeFrais() {
		return typeFrais;
	}

	public void setTypeFrais(String typeFrais) {
		this.typeFrais = typeFrais;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public boolean isSupprime() {
		return supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	
	
   
}
