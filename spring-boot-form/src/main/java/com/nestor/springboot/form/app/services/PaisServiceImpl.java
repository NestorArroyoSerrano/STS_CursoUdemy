package com.nestor.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nestor.springboot.form.app.models.domain.Pais;

import jakarta.validation.ConstraintValidator;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista = Arrays.asList(
				new Pais (1, "ES", "España"), 
				new Pais (2, "MX", "México"),
				new Pais (3, "CL", "Chile"), 
				new Pais (4, "AR", "Argentina"), 
				new Pais (5, "PE", "Perú"), 
				new Pais (6, "CO", "Colombia"), 
				new Pais (7, "VE", "Venezuela"));
	}

	@Override
	public List<Pais> listar() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
	    for (Pais pais : this.lista) {
	        if (id.equals(pais.getId())) { // Cambiado de == a equals
	            return pais; // Devuelve el país si el id coincide
	        }
	    }
	    return null; // Devuelve null si no se encuentra ningún país con el id proporcionado
	}

}
