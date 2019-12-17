package org.habi2.banqueProjet2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.habi2.banqueProjet2.entities.Client;
import org.habi2.banqueProjet2.entities.Compte;
import org.habi2.banqueProjet2.entities.Employe;
import org.habi2.banqueProjet2.entities.Groupe;
import org.habi2.banqueProjet2.entities.Operation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public class BanqueDaoImpl implements IBanqueDao{
	@PersistenceContext
	private EntityManager em ;
	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if(codeSup!= null) {
			Employe sup=em.find(Employe.class , codeSup);
			e .setEmployeSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		Employe e=em.find(Employe.class, codeEmp);
		Groupe g=em.find(Groupe.class, codeGr);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
	}

	@Override
	public Compte addCompte(Compte c, Long codeClient, Long CodeEmploye) {
		Client client= em.find(Client.class, codeClient);
		Employe employe= em.find(Employe.class, CodeEmploye);
		c.setClient(client);
		c.setEmploye(employe);
		em.persist(c);
		return c;
	}

	@Override
	public Operation addOperation(Operation op, String codeCompte, Long codeEmploye) {
		Compte compte = em.find(Compte.class, codeCompte);
		Employe employe = em.find(Employe.class, codeEmploye);
		op.setCompte(compte);
		op.setEmploye(employe);
		em.persist(op);
		return op;
	}

	@Override
	public Compte getCompte(String codeCompte) {
		Compte compte = em.find(Compte.class, codeCompte);
		if(compte==null) throw new RuntimeException("compte introuvable");
		return compte ;
	}

	@Override
	public List<Operation> getOperationsCompte(String codeCompte) {
		Query req = em.createQuery("select o from Operation o where o.compte.codeCompte = :x ");
		req.setParameter("x", codeCompte);
		// req.setFirstResult(pos);
		// req.setMaxResults(nbOp);
		return req.getResultList();
	}

	@Override
	public Client getClient(Long codeClient) {
		Client client = em.find(Client.class, codeClient);
		if(client==null) throw new RuntimeException("client introuvable");
		return null;
	}

	@Override
	public List<Client> getClientsParMotClé(String mc) {
		Query req = em.createQuery("select c from Client c where c.nomClient like :x");
		req.setParameter("x", "%" + mc + "%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesClient(Long codeClient) {
		Query req = em.createQuery("select c from Compte c where c.client.codeClient = :x");
		req.setParameter("x", codeClient);
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesCreerParEmploye(Long codeEmploye) {
		Query req = em.createQuery("select c from Compte c where c.employe.codeEmploye = :x");
		req.setParameter("x", codeEmploye);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		Query req = em.createQuery("select e from Employe e");
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {
		Query req = em.createQuery("select g from Groupe g");
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployesGroupe(String codegroupe) {
		Query req = em.createQuery("select e from Employe e where e.groupes.codeGroupe = :x");
		req.setParameter("x", codegroupe);
		return req.getResultList();
	}
	/* @Override
	public long getNombreOperations(String codeCpte) {
		Query req = em.createQuery("select count(o) from Operation o where o.compte.codeCompte = :x");
		req.setParameter("x", codeCpte);
		return (Long) req.getResultList().get(0);
	} */
	/*@Override */
	public long getNombreOperations(String codeCpte) {
		Query req = em.createQuery("select count(o) from Operation o where o.compte.codeCompte = :x");
		req.setParameter("x", codeCpte);
		return (Long) req.getResultList().get(0);
	} 
	@Override
	public List<Operation> consulterOperations(String codeCpte, int pos, int nbOp) {
		Query req = em.createQuery("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc");
		req.setParameter("x", codeCpte);
		req.setFirstResult(pos);
		req.setMaxResults(nbOp);
		return req.getResultList();
	}


}
