package cl.andres.java.cursos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.andres.java.cursos.repository.AdministradorRepository;
import cl.andres.java.cursos.repository.CursoRepository;
import cl.andres.java.cursos.repository.UsuarioRepository;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	AdministradorRepository administradorRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/ingreso")
	public String login() {
		return "ingreso";
	}
	
	@GetMapping("/salir")
	public String logout() {
		return "logout";
	}
	
	
}
