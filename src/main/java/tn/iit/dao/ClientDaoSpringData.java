package tn.iit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.iit.entity.Client;

@Repository
public interface ClientDaoSpringData extends JpaRepository<Client, String> {
	public Client findClientByCin(String cin);

	public List<Client> findByNomContaining(String nom);
}
