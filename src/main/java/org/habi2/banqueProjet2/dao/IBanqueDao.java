package org.habi2.banqueProjet2.dao;

import java.util.List;

import org.habi2.banqueProjet2.entities.Client;
import org.habi2.banqueProjet2.entities.Compte;
import org.habi2.banqueProjet2.entities.Employe;
import org.habi2.banqueProjet2.entities.Groupe;
import org.habi2.banqueProjet2.entities.Operation;

public interface IBanqueDao {
	// add
		 public Client addClient(Client c);
		 public Employe addEmploye(Employe e, Long codeSup);
		 public Groupe addGroupe(Groupe g);
		 public void addEmployeToGroupe(Long codeEmp,Long codeGr);
		 public Compte addCompte(Compte c , Long codeClient ,Long CodeEmploye);
	     public Operation addOperation(Operation op , String codeCompte , Long codeEmploye);
	     
	     
	// get
		 public Compte getCompte(String codeCompte);
		 public List<Operation> getOperationsCompte(String codeCompte);
		 public Client getClient(Long codeClient);
		 public List<Client>  getClientsParMotClé(String mc);
		 public List<Compte> getComptesClient(Long codeClient);
		 public List<Compte> getComptesCreerParEmploye(Long codeEmploye);
		 public List<Employe>  getEmployes();
		 public List<Groupe>  getGroupes();
	     public List<Employe> getEmployesGroupe(String codegroupe);
	 	 public long getNombreOperations(String codeCpte);
	     public List<Operation> consulterOperations(String codeCpte, int position, int nbOp);
	 //	public long getNombreOperations(String codeCpte);
}
