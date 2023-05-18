package sn.intouch.gu.api.ejb.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "service_category")
public class CategoryS implements Serializable{
	
	private String service_C_nom, service_C_code;
	private Long service_C_id;
	Boolean supprime = false ;
//	private NetworkGroup networkgroup_id;
	
	@Id
	@Column(name = "service_c_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getService_C_id() {
		return service_C_id;
	}
	
	public void setService_C_id(Long service_C_id) {
		this.service_C_id = service_C_id;
	}
	
	@Column(name = "service_c_code")
	public String getService_C_code() {
		return service_C_code;
	}
	
	public void setService_C_code(String service_C_code) {
		this.service_C_code = service_C_code;
	}
	@Column(name = "service_c_nom")
	public String getService_C_nom() {
		return service_C_nom;
	}
	
	public void setService_C_nom(String service_C_nom) {
		this.service_C_nom = service_C_nom;
	}
	
	@Column(name = "fnct_c_sup")
	public Boolean getSupprime() {
		return supprime;
	}
	
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "networkgroup_id")
//	public NetworkGroup getNetworkgroup_id() {
//		return networkgroup_id;
//	}
//
//	public void setNetworkgroup_id(NetworkGroup networkgroup_id) {
//		this.networkgroup_id = networkgroup_id;
//	}
}
	
	
	
	
	