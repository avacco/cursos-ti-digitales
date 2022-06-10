package cl.andres.java.cursos.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.andres.java.cursos.model.Curso;
import cl.andres.java.cursos.model.Estudiante;
import cl.andres.java.cursos.repository.CursoRepository;
import cl.andres.java.cursos.repository.EstudianteRepository;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/index")
	public String panelEstudiante(Estudiante estudiante, Principal principal, Model modelo) {
		
		Optional<Estudiante> estudiantePrincipal = estudianteRepository.findByRut(principal.getName());
		if(estudiantePrincipal != null) {
			modelo.addAttribute(estudiantePrincipal.get());
			return "estudiante/panel";
		}
		return "estudiante/panel";
	}

	
	@GetMapping("/postular/{id}/{cursoId}")
	public String postular(@PathVariable("id") Long id, @PathVariable("cursoId") Long cursoId) {
		Optional<Estudiante> estudiante = estudianteRepository.findById(id);
		if(estudiante.get().getCurso() == null) {
			Optional<Curso> curso = cursoRepository.findById(cursoId);
			estudiante.get().setCurso(curso.get());
			estudianteRepository.saveAndFlush(estudiante.get());
			return "redirect:/estudiante/index";
		}		
		return "redirect:/";
	}
}
