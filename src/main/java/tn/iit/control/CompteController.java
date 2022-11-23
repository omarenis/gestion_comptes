package tn.iit.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.entity.Client;
import tn.iit.entity.Compte;
import tn.iit.service.ClientService;
import tn.iit.service.CompteService;

@Controller
@RequestMapping("/comptes")
public class CompteController {

	public final CompteService compteService;
	public final ClientService clientService;

	@Autowired
	public CompteController(CompteService compteService,ClientService clientService) {
		super();
		this.compteService = compteService;
		this.clientService = clientService;
	}

	@PostMapping("/save")
	public ModelAndView save(CompteForm compteForm) {
		Client client;
		ModelAndView modelAndView = new ModelAndView();
		try {
			client = clientService.findById(compteForm.getClientCin());
			if(client == null)
			{
				modelAndView.addObject("message", "client not found");
				modelAndView.setViewName("404");
			} else
			{
				Compte compte =new Compte(compteForm.getSolde(), client); 
				compteService.saveOrUpdate(compte);
				modelAndView.setViewName("redirect:/comptes/all");
				
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			modelAndView.addObject("message", "cliebnt not found");
			modelAndView.setViewName("404");
		}
		return modelAndView;
	}

	@GetMapping("/all")
	public ModelAndView getAll() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comptes", compteService.findAll());
		modelAndView.setViewName("comptes");// go to comptes.html (sans extension)
		return modelAndView;
	}

	@GetMapping("/edit/{rib}")
	public ModelAndView edit(@PathVariable(name = "rib") Long rib) {
		ModelAndView modelAndView = new ModelAndView();
		Compte compte = compteService.findById(rib);
		if(compte == null)
		{
			modelAndView.addObject("messazge", "compte not found");
			modelAndView.setViewName("404");
		} else
		{
			CompteForm compteForm = new CompteForm();
			compteForm.setClientCin(compte.getClient().getCin());
			compteForm.setSolde(compte.getSolde());
			compteForm.setRib(compte.getRib());
			modelAndView.addObject("compte", compteForm); 
			modelAndView.setViewName("edit-compte");// go to hello.html (sans extension)
		}
		return modelAndView;
	}

	@PostMapping("/delete-ajax")
	public String deleteAjax(Long rib) {
		compteService.delete(rib);
		return "redirect:/comptes/all";
	}

	@PostMapping(value = "/update")
	public String update(Compte compteForm) {
		Compte compte = compteService.findById(compteForm.getRib());
		compte.setSolde(compteForm.getSolde());
		compteService.saveOrUpdate(compte);
		return "redirect:/comptes/all";
	}

	@GetMapping("/all-json")

	@ResponseBody // le retour est json (api jackson)
	public List<Compte> getAllJson() {
		return compteService.findAll();
	}

}
