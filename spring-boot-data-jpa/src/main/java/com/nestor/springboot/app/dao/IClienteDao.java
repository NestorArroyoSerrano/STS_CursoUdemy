package com.nestor.springboot.app.dao;

import java.util.List;

import com.nestor.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
}
