package org.habi2.banqueProjet2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Operation implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroOperation ;
	private Date dateOperation ;
	private double montant ;
	@ManyToOne
	@JoinColumn(name="CODE_COMPTE")
	private Compte compte ;
	@ManyToOne
	@JoinColumn(name="CODE_EMPLOYE")
	private Employe employe ;
	
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(Date dateOperation, double montant) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
	}
	
	public long getNumeroOperation() {
		return numeroOperation;
	}
	public void setNumeroOperation(long numeroOperation) {
		this.numeroOperation = numeroOperation;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	

}
