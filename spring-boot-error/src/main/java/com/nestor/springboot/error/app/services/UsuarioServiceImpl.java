package com.nestor.springboot.error.app.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.nestor.springboot.error.app.models.domain.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	
	private List<Usuario> lista;
	
	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1, "Andrés", "Guzmán"));
		this.lista.add(new Usuario(2, "Pepa", "García"));
		this.lista.add(new Usuario(3, "Samuel", "Primitivo"));
		this.lista.add(new Usuario(4, "Aarón", "Fuentes"));
		this.lista.add(new Usuario(5, "César", "Arroyo"));
		this.lista.add(new Usuario(6, "Silvia", "Martínez"));
		this.lista.add(new Usuario(7, "Alicia", "Ortiz"));
	}

	@Override
	public List<Usuario> listar() {

		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		for(Usuario u: this.lista) {
			if(u.getId().equals(id)) {
				resultado = u;
				break;
			}
		}
		return resultado;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		Usuario usuario = this.obtenerPorId(id);
		return Optional.ofNullable(usuario);
	}

}
