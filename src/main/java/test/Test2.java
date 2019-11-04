package test;

import java.util.List;

import org.habi2.banqueProjet2.entities.Compte;
import org.habi2.banqueProjet2.entities.Operation;
import org.habi2.banqueProjet2.metier.IBanqueMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");
		Compte cp = metier.getCompte("CC1");
		
		System.out.println("Solde" + cp.getSolde());
		System.out.println("Date" + cp.getDateCreation());
		System.out.println("Client" + cp.getClient().getNomClient());
		System.out.println("Employe" + cp.getEmploye().getNomEmploye());
		List<Operation> ops = metier.getOperationsCompte("CC1");
		for (Operation op:ops) {
			System.out.println("---------------");
			System.out.println(op.getNumeroOperation());
			System.out.println(op.getDateOperation());
			System.out.println(op.getMontant());
			System.out.println(op.getClass().getSimpleName());

		}

	}

}
