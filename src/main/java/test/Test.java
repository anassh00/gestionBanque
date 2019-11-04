package test;

import java.util.Date;

import org.habi2.banqueProjet2.entities.Client;
import org.habi2.banqueProjet2.entities.CompteCourant;
import org.habi2.banqueProjet2.entities.CompteEpargne;
import org.habi2.banqueProjet2.entities.Employe;
import org.habi2.banqueProjet2.entities.Groupe;
import org.habi2.banqueProjet2.metier.IBanqueMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");				
					IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");
					
					// tester 
					metier.addClient(new Client ("C1","AD1"));
					metier.addClient(new Client ("C2","AD2"));
					metier.addEmploye(new Employe("E1"), null);
					metier.addEmploye(new Employe("E2"), 1L);
					metier.addEmploye(new Employe("E3"), 1L);
					metier.addGroupe(new Groupe("G1"));
					metier.addGroupe(new Groupe("G2"));
					metier.addEmployeToGroupe(1L, 1L);
					metier.addEmployeToGroupe(2L, 2L);
					metier.addEmployeToGroupe(3L, 2L);
					metier.addCompte(new CompteCourant("CC1", new Date() , 5000 , 3000), 1L, 2L);
					metier.addCompte(new CompteEpargne("CE1", new Date() , 60000 , 5), 2L, 2L);
					
					metier.verser(5000, "CC1", 2L);
					metier.retirer(3000, "CC1", 2L);
					metier.virement(2000, "CC1", "CE1", 1L);

	}

}
