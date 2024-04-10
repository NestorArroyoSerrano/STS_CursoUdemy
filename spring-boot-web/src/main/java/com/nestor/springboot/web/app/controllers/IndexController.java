package com.nestor.springboot.web.app.controllers;

//import java.util.Map;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
import com.nestor.springboot.web.app.models.Usuario;


@Controller
@RequestMapping("/app")
public class IndexController {
	
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;

	@GetMapping({"/index", "/", "", "/home"})
	// Distintas formas de mapear 
	/*
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("titulo", "Hola Spring Framework con ModelAndView");
		mv.setViewName("index");
		return mv;
		*/
	/*
	public String index(ModelMap model) {
		model.addAttribute("titulo", "Hola Spring Framework con Model");
		return "index";
		*/
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Néstor");
		usuario.setApellido("Arroyo");
		usuario.setEmail("nestorarroyoserrano@gmail.com");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {		
		model.addAttribute("titulo", textoListar);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(new Usuario("Néstor", "Arroyo", "nestorarroyoserrano@gmail.com"),
				new Usuario("Pedro", "Ramirez", "pedritoramirez@gmail.com"),
				new Usuario("Alicia", "Ortiz", "alicitaortiz@gmail.com"),
				new Usuario("Juan", "Rodriguez", "juanitorodriguez@gmail.com"));
		
		return usuarios;
	}
}
