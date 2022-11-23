package tn.iit.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.entity.Client;
import tn.iit.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

	public final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "cin") String cin, @RequestParam(name = "nom") String nom,
			@RequestParam(name = "prenom") String prenom, @RequestParam(name="addresse") String addresse) {
		Client client =new Client(cin, prenom, nom, addresse);
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

	@GetMapping("/all")
	public ModelAndView getAll(@RequestParam(name="nom", required=false, value="") String nom) {
		ModelAndView modelAndView = new ModelAndView();
		List<Client> clients;
		System.out.print(nom == null);
		if(nom != null && nom != "" )
		{
			clients =  clientService.findClientByNom(nom);
		}
		else
		{
			clients = clientService.findAll();
		}
		System.out.print(clients);
		modelAndView.addObject("clients", clients);
		modelAndView.setViewName("clients");// go to clients.html (sans extension)
		return modelAndView;
	}

	@GetMapping("/edit/{cin}")
	public ModelAndView edit(@PathVariable(name = "cin") String cin) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("client", clientService.findClientByCin(cin));
			modelAndView.setViewName("client-edit");// go to hello.html (sans extension)
		} catch (Exception e) {
			modelAndView.addObject("message", e.getMessage());// si le client n'existe pad on retourne le 404 template
			modelAndView.setViewName("404");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@PostMapping("/edit")
	public ModelAndView editClient(Client client)
	{
		ModelAndView modelAndView  = new ModelAndView();
		modelAndView.setViewName("redirect:/clients/all");
		clientService.saveOrUpdate(client);
		return modelAndView;	
	}

	@GetMapping("/delete/{cin}")
	public String delete(@PathVariable(name = "cin") String cin) {
		clientService.delete(cin);
		return "redirect:/clients/all";
	}

	@PostMapping("/delete-ajax")
	public String deleteAjax(String cin) {
		clientService.delete(cin);
		return "redirect:/clients/all";
	}

	@PostMapping(value = "/update")
	public String update(@ModelAttribute Client client) {
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

	@GetMapping("/all-json")

	@ResponseBody // le retour est json (api jackson)
	public List<Client> getAllJson() {
		return clientService.findAll();
	}
}
