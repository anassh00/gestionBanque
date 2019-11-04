package org.habi2.banqueProjet2.metier;


import java.util.Date;
import java.util.List;

import org.habi2.banqueProjet2.dao.IBanqueDao;
import org.habi2.banqueProjet2.entities.Client;
import org.habi2.banqueProjet2.entities.Compte;
import org.habi2.banqueProjet2.entities.Employe;
import org.habi2.banqueProjet2.entities.Groupe;
import org.habi2.banqueProjet2.entities.Operation;
import org.habi2.banqueProjet2.entities.Retrait;
import org.habi2.banqueProjet2.entities.Versment;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	private IBanqueDao dao ;
	
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		// TODO Auto-generated method stub
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		// TODO Auto-generated method stub
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		dao.addEmployeToGroupe(codeEmp, codeGr);
	}

	@Override
	public Compte addCompte(Compte c, Long codeClient, Long CodeEmploye) {
		// TODO Auto-generated method stub
		return dao.addCompte(c, codeClient, CodeEmploye);
	}

	@Override
	public void verser(double montant, String compte, Long codeEmploye) {
		dao.addOperation(new Versment(new Date(), montant), compte , codeEmploye);
		Compte cp = dao.getCompte(compte);
		cp.setSolde(cp.getSolde()+montant);
	}

	@Override
	public void retirer(double montant, String compte, Long codeEmploye) {
		dao.addOperation(new Retrait(new Date(), montant), compte , codeEmploye);
		Compte cp = dao.getCompte(compte);
		cp.setSolde(cp.getSolde()-montant);		
	}

	@Override
	public void virement(double montant, String compte1, String compte2, Long codeEmploye) {
		retirer(montant, compte1, codeEmploye);
		retirer(montant, compte2, codeEmploye);
	}

	@Override
	public Compte getCompte(String codeCompte) {
		// TODO Auto-generated method stub
		return dao.getCompte(codeCompte);
	}

	@Override
	public List<Operation> getOperationsCompte(String codeCompte) {
		// TODO Auto-generated method stub
		return dao.getOperationsCompte(codeCompte);
	}
	/* @Override
	public long getNombreOperations(String codeCpte) {
		return dao.getNombreOperations(codeCpte);
	} */

	@Override
	public Client getClient(long codeClient) {
		// TODO Auto-generated method stub
		return dao.getClient(codeClient);
	}

	@Override
	public List<Client> getClientsParMotClé(String mc) {
		// TODO Auto-generated method stub
		return dao.getClientsParMotClé(mc);
	}

	@Override
	public List<Compte> getComptesClient(Long codeClient) {
		// TODO Auto-generated method stub
		return dao.getComptesClient(codeClient);
	}

	@Override
	public List<Compte> getComptesCreerParEmploye(Long codeEmploye) {
		// TODO Auto-generated method stub
		return dao.getComptesCreerParEmploye(codeEmploye);
	}

	@Override
	public List<Employe> getEmployes() {
		// TODO Auto-generated method stub
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {
		// TODO Auto-generated method stub
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesGroupe(String codegroupe) {
		// TODO Auto-generated method stub
		return dao.getEmployesGroupe(codegroupe);
	}
    //-------------------------- 
/*	@Override
	public List<Operation> consulterOperations(String codeCpte, int pos, int nbOp) {
		return dao.consulterOperations(codeCpte,pos, nbOp);
	}
 
	@Override
	public long getNombreOperations(String codeCpte) {
		return dao.getNombreOperations(codeCpte);
	} */
}
