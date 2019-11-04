package org.habi2.banqueProjet2.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("V")
public class Versment extends Operation {

	public Versment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versment(Date dateOperation, double montant) {
		super(dateOperation, montant);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Versement";
	}

}
