package cl.andres.java.cursos.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.andres.java.cursos.model.Curso;
import cl.andres.java.cursos.repository.CursoRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/index")
	public String panelAdmin(Curso curso, Model modelo) {
		List<Curso> cursos = cursoRepository.findAll();
		modelo.addAttribute("cursos",cursos);
		return "administracion/panel";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCurso(@PathVariable(name = "id") Curso curso, Model modelo) {
		modelo.addAttribute("curso",curso);
		return "administracion/nuevocurso";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCurso(@PathVariable(name="id") Long id) {
		cursoRepository.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/nuevocurso")
	public String crearCurso(Curso curso) {
		return "administracion/nuevocurso";
	}
	
	@PostMapping("/nuevocurso")
	public String procesarCurso(@Valid Curso curso, BindingResult validacion, @RequestParam("image") MultipartFile imagen) throws IOException {
		if (validacion.hasErrors()) return "administracion/nuevocurso";
		
		byte[] contenidoImagen = imagen.getBytes();
		
		if(curso.getId() == null) {
			Curso agregarCurso = Curso.builder()
										.nombre(curso.getNombre())
										.fechaInicio(curso.getFechaInicio())
										.fechaFin(curso.getFechaFin())
										.cupos(curso.getCupos())
										.descripcion(curso.getDescripcion())
										.imagen(contenidoImagen)
										.build()
									;
			cursoRepository.saveAndFlush(agregarCurso);
			return "redirect:/";
		}else {
			curso.setImagen(contenidoImagen);
			cursoRepository.saveAndFlush(curso);
			return "redirect:/";
		}
	}
	
}
