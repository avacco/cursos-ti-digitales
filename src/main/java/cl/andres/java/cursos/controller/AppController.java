package cl.andres.java.cursos.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.andres.java.cursos.model.Curso;
import cl.andres.java.cursos.model.Estudiante;
import cl.andres.java.cursos.repository.AdministradorRepository;
import cl.andres.java.cursos.repository.CursoRepository;
import cl.andres.java.cursos.service.UsuarioService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/")
	public String index(Curso curso, Model modelo) {
		List<Curso> cursos = cursoRepository.findAll();
		modelo.addAttribute("cursos",cursos);
		return "index";
	}
	
	@GetMapping("/imagen/{id}")
	public ResponseEntity<byte[]> muestraImagenes(@PathVariable("id") Long id) throws SQLException {
		Optional<Curso> curso = cursoRepository.findById(id);
		byte[] imageBytes = null;
		if(curso.isPresent()) {
			imageBytes = curso.get().getImagen();
		}
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	@GetMapping("/registro")
	public String registrarEstudiante(Estudiante estudiante) {
		return "registro";
	}
	
	@PostMapping("/registro")
	public String procesarRegistro(@Valid Estudiante estudiante, BindingResult validacion) {
		if(validacion.hasErrors()) return "registro";
		
		usuarioService.crearUsuario(estudiante);
		return "redirect:/";
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
