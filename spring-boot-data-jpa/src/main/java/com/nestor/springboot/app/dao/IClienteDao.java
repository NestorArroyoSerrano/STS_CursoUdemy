package com.nestor.springboot.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nestor.springboot.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
