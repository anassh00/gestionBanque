package org.habi2.banqueProjet2.Controlleur;


import java.util.List;


import javax.validation.Valid;

import org.habi2.banqueProjet2.entities.Compte;
import org.habi2.banqueProjet2.entities.Operation;
import org.habi2.banqueProjet2.metier.IBanqueMetier;
import org.habi2.banqueProjet2.models.BanqueForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class banqueController {
	@Autowired
	private IBanqueMetier metier;
	
	@RequestMapping(value="/index")
	public String index(Model model) {
		model.addAttribute("banqueForm", new BanqueForm());
		return "banque";
	}	
	
	@RequestMapping(value="/chargerCompte")
	public String chargerCompte( BanqueForm bf,  Model model) {
		
	/*	if (bindingResult.hasErrors()) {
			return "banque";
		} */
		chargerCompte(bf);
		model.addAttribute("banqueForm", bf);
		return "banque";
	}
	
	@RequestMapping(value="/saveOperation")
	public String saveOperation(@Valid  @ModelAttribute("banqueForm") BanqueForm bf) {
		
			try {
				/* if (bindingResult.hasErrors()) {
					return "banque";
				} */
				if (bf.getAction() != null) {
					if (bf.getTypeOperation().equals("VERSEMENT")) {
						metier.verser(bf.getMontant(), bf.getCode(), 1L);
					} else if (bf.getTypeOperation().equals("RETRAIT")) {
						metier.retirer(bf.getMontant(), bf.getCode(), 1L);
					} else if (bf.getTypeOperation().equals("VIREMENT")) {
						metier.virement(bf.getMontant(), bf.getCode(),
								bf.getCode2(), 1L);
					}
				}
			} catch (Exception e) {
				bf.setException(e.getMessage());
			}
			chargerCompte(bf);
			
		return "banque";
    } 
// a revoir	
	 public void chargerCompte(BanqueForm bf) {
			try {
				Compte compte = metier.getCompte(bf.getCode());
				bf.setTypeCompte(compte.getClass().getSimpleName());
				bf.setCompte(compte);
				int pos = bf.getNbLignes() * bf.getPage();				
			//	List<Operation> ops = metier.consulterOperations(bf.getCode(), pos, bf.getNbLignes());
			//	bf.setOperations(ops);
			//	long npOp = metier.getNombreOperations(bf.getCode());
			//	bf.setNbpages((int)(npOp/bf.getNbLignes())+1);
			} catch (Exception e) {
				bf.setException(e.getMessage());
			}

	} 

}







/*
@Controller
public class banqueController {
	@Autowired
	private IBanqueMetier metier ;
	@RequestMapping(value="/index")
	public String index(Model model) {
		model.addAttribute("banqueForm",new banqueModel());
		return "banque";
	} //
	@RequestMapping(value="/chargerCompte")
	public String charger( banqueModel bm, //@Valid @ModelAttribute("banqueForm") // BindingResult bindingResult , 
			Model model) {
		/*if (bindingResult.hasErrors()) {
			return "banque";
		} */
/*		try {
			Compte cp = metier.getCompte(bm.getCode());
			bm.setTypeCompte(cp.getClass().getSimpleName());
			bm.setCompte(cp);
			List<Operation> ops=metier.getOperationsCompte(bm.getCode());
			bm.setOperations(ops);
		} catch (Exception e) {
			bm.setException(e.getMessage());
		}
		
		model.addAttribute("banqueForm",bm);
		return "banque";
		
	}
	@RequestMapping(value="/saveOperation")
	public String saveOperation(@Valid @ModelAttribute("banqueForm") banqueModel bm ) { //,Model //model) {
		
			
			
				if (bm.getAction() != null) {
					if (bm.getTypeOperation().equals("VERSEMENT")) {
						metier.verser(bm.getMontant(), bm.getCode(), 1L);
					} else if (bm.getTypeOperation().equals("RETRAIT")) {
						metier.retirer(bm.getMontant(), bm.getCode(), 1L);
					} else if (bm.getTypeOperation().equals("VIREMENT")) {
						metier.virement(bm.getMontant(), bm.getCode(),
								bm.getCode2(), 1L);
					}
				}
				try {
					Compte cp = metier.getCompte(bm.getCode());
					bm.setTypeCompte(cp.getClass().getSimpleName());
					bm.setCompte(cp);
					List<Operation> ops=metier.getOperationsCompte(bm.getCode());
					bm.setOperations(ops);
				} catch (Exception e) {
					bm.setException(e.getMessage());
				}
		//model.addAttribute("banqueForm",bm);	
		return "banque";
    } */
	/*@RequestMapping(value="/saveOperation")
	public String saveOperation(banqueModel bf, BindingResult bindingResult) {
		
			try {
				if (bindingResult.hasErrors()) {
					return "banque";
				}
				if (bf.getAction() != null) {
					if (bf.getTypeOperation().equals("VERSEMENT")) {
						metier.verser(bf.getMontant(), bf.getCode(), 1L);
					} else if (bf.getTypeOperation().equals("RETRAIT")) {
						metier.retirer(bf.getMontant(), bf.getCode(), 1L);
					} else if (bf.getTypeOperation().equals("VIREMENT")) {
						metier.virement(bf.getMontant(), bf.getCode(),
								bf.getCode2(), 1L);
					}
				}
			} catch (Exception e) {
				bf.setException(e.getMessage());
			}
			chargerCompte(bf);
			
		return "banque";
    }
	
	public void chargerCompte(banqueModel bf) {
			try {
				Compte compte = metier.getCompte(bf.getCode());
				bf.setTypeCompte(compte.getClass().getSimpleName());
				bf.setCompte(compte);
				int pos = bf.getNbLignes() * bf.getPage();				
				List<Operation> ops = metier.getOperationsCompte(bf.getCode(), pos, bf.getNbLignes());
				bf.setOperations(ops);
				long npOp = metier.getNombreOperations(bf.getCode());
				bf.setNbpages((int)(npOp/bf.getNbLignes())+1);
			} catch (Exception e) {
				bf.setException(e.getMessage());
			}

	} */

/*	
}
*/