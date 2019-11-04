package org.habi2.banqueProjet2.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="CLIENTS")
public class Client implements Serializable {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_CLIENT")
	private long codeClient ;
	private String nomClient ;
	private String addressClient ;
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	private Collection<Compte> compte ;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Client(String nomClient, String addressClient) {
		super();
		this.nomClient = nomClient;
		this.addressClient = addressClient;
	}

	public long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(long codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAddressClient() {
		return addressClient;
	}

	public void setAddressClient(String addressClient) {
		this.addressClient = addressClient;
	}

	public Collection<Compte> getCompte() {
		return compte;
	}

	public void setCompte(Collection<Compte> compte) {
		this.compte = compte;
	}
	
	
	
	
}
