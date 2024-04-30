/*
package com.nestor.springboot.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.nestor.springboot.app.models.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Cliente findOne(Long id) {
		
		return em.find(Cliente.class, id);
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<Cliente> findAll() {

		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		if(cliente.getId() !=null && cliente.getId() >0) {
			em.merge(cliente);
		}else {
		em.persist(cliente);
		}
		
	}

	@Override
	public void delete(Long id) {
		// Cliente cliente = findOne(id);
		em.remove(findOne(id));
	}

}
*/