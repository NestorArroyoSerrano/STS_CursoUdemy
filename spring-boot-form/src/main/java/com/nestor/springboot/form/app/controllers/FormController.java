package com.nestor.springboot.form.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.nestor.springboot.form.app.editors.NombreMayusculaEditor;
import com.nestor.springboot.form.app.models.domain.Usuario;
import com.nestor.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);  // "Lenient" en inglés significa tolerante o permisivo. Por lo tanto, cuando setLenient se establece en true, el analizador de fechas será tolerante con ciertos errores o discrepancias en el formato de entrada.
										//Cuando setLenient se establece en false, el analizador de fechas será menos tolerante y requerirá que la entrada coincida exactamente con el formato especificado.
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setApellido("Martínez");
		usuario.setIdentificador("123.456.789-K");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}
	
	/*
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) { 
//			@RequestParam(name="username") String username, 
//			@RequestParam String password,
//			@RequestParam String email) {	
		
		model.addAttribute("titulo", "Resultado form");
		if(result.hasErrors()) {
//			Map<String, String> errores = new HashMap<>();
//			result.getFieldErrors().forEach(err -> {
//				errores.put(err.getField(), "El campo ".concat(err.getField()).concat("").concat(err.getDefaultMessage()));
//			});
//			model.addAttribute("error", errores);
			return "form";
		}
		model.addAttribute("usuario", usuario);
				
		return "resultado";
	}
	*/
	
	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		validador.validate(usuario, result);
	    model.addAttribute("titulo", "Resultado form");
	    
	    if (result.hasErrors()) {
	        // Aquí estás devolviendo el objeto BindingResult en el modelo
	        model.addAttribute("org.springframework.validation.BindingResult.usuario", result);
	        return "form";
	    }
	    model.addAttribute("usuario", usuario);
	    status.setComplete();
	    return "resultado";
	}
}
