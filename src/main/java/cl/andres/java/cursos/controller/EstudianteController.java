package cl.andres.java.cursos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			if(curso.get().getCupos() > 0) {
				estudiante.get().setCurso(curso.get());
				curso.get().setCupos(curso.get().getCupos()-1);
				cursoRepository.save(curso.get());
				estudianteRepository.saveAndFlush(estudiante.get());
				return "redirect:/estudiante/index";
			}
			return "redirect:/";
		}		
		return "redirect:/";
	}
	
	@GetMapping("/imagen/{id}")
	public ResponseEntity<byte[]> muestraImagenes(@PathVariable("id") Long id) throws SQLException, IOException {
		Optional<Estudiante> estudiante = estudianteRepository.findById(id);
		byte[] imageBytes = null;
		if(estudiante.isPresent()) {
			imageBytes = estudiante.get().getImagen();
			if(imageBytes == null) {
				imageBytes = Files.readAllBytes(Paths.get("src/main/resources/static/img/placeholder.png"));
			}
		}
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	@PostMapping("/subirimagen")
	public String procesarImagen(@RequestParam("image") MultipartFile imagen, Principal principal) throws IOException {
		byte[] contenidoImagen = imagen.getBytes();
		
		Optional<Estudiante> estudiantePrincipal = estudianteRepository.findByRut(principal.getName());
		estudiantePrincipal.get().setImagen(contenidoImagen);
		estudianteRepository.saveAndFlush(estudiantePrincipal.get());
		
		return "redirect:/estudiante/index";
	}
}
