package com.nestor.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nestor.springboot.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
