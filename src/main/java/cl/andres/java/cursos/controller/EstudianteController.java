package cl.andres.java.cursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

	@GetMapping("/index")
	public String panelEstudiante() {
		return "estudiante/panel";
	}
	
	@GetMapping("/postular")
	public String postular() {
		
		return "redirect:/";
	}
}
