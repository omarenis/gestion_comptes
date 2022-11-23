package tn.iit;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.iit.dao.ClientDaoSpringData;
import tn.iit.dao.CompteDaoSpringData;
import tn.iit.entity.Compte;

@SpringBootApplication
public class FirstSpringBootApplication implements CommandLineRunner {

	@Autowired
	CompteDaoSpringData daoCompte;
	@Autowired
	ClientDaoSpringData daoClient;

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		/*
		 * Client client1 = new Client("088", "H", "N");
		 * daoClient.saveAndFlush(client1); Compte compte = new Compte(1L, 100,
		 * client1); daoCompte.saveAndFlush(compte); Compte compte2 = new Compte(2L,
		 * 500, client1); daoCompte.saveAndFlush(compte2);
		 */

	/*	String cin = "088"; // Comment récupéré le compte
		// unidirectionnelle : requête pour récupérer les comptes d'un client
		List<Compte> comptes = daoCompte.findByClientCin(cin);
		System.out.println(comptes);
		System.out.println("-------------------------");
		// bidirectionnelle
		Client client10 = daoClient.findById(cin).get();
		System.out.println(client10.getComptes());
*/
	}

}
