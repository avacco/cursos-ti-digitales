package cl.andres.java.cursos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.andres.java.cursos.model.Administrador;
import cl.andres.java.cursos.model.Curso;
import cl.andres.java.cursos.model.Estudiante;
import cl.andres.java.cursos.repository.CursoRepository;
import cl.andres.java.cursos.service.AdministradorService;
import cl.andres.java.cursos.service.UsuarioService;

@SpringBootApplication
public class CursosTiDigitalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosTiDigitalesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner datosIniciales(AdministradorService aService, UsuarioService uService, CursoRepository cRepo) {
		return args -> {
			if(aService.contarAdmin() == 0) {
				Administrador admin = Administrador.builder()
													.username("admin")
													.password("1234")
													.build();
				aService.crearAdmin(admin);
														
			}
			
			if(uService.contarUsuarios() == 0) {
				Estudiante estudiante = Estudiante.builder()
											.nombre1("Pedro")
											.nombre2("Pablo")
											.apellidoPaterno("Bucaretti")
											.apellidoMaterno("Colina")
											.direccion("No disponible")
											.region("No disponible")
											.telefono("912345678")
											.email("correo@dominio.com")
											.rut("12345678-9")
											.password("1234")
											.region("No disponible")
											.comuna("No disponible")
											.build()
									;
				uService.crearUsuario(estudiante);
			}
			if(cRepo.count() == 0) {
				Curso cursoJava = Curso.builder()
										.nombre("Fundamentos de Programacion en Java")
										.fechaInicio(LocalDate.of(2023, 3, 3))
										.fechaFin(LocalDate.of(2023, 12, 20))
										.cupos(30)
										.descripcion("Curso de fundamentos de programación con Java. Se enseñará los fundamentos básicos, primero creando aplicaciones por consola, para pasar luego a aplicaciones con interfaces de usuario.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/java.jpg")))
										.build();
				Curso cursoSpring = Curso.builder()
										.nombre("Desarrollo Web con Spring Framework")
										.fechaInicio(LocalDate.of(2023, 3, 3))
										.fechaFin(LocalDate.of(2023, 12, 20))
										.cupos(30)
										.descripcion("Desarrollo web con Spring Framework, una plataforma Java de código abierto que proporciona un soporte integral de infraestructura para desarrollar aplicaciones Java robustas de manera muy fácil y rápida.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/spring.jpg")))
										.build();
				Curso cursoOracle = Curso.builder()
										.nombre("Bases de Datos Relacionales con Oracle")
										.fechaInicio(LocalDate.of(2023, 3, 3))
										.fechaFin(LocalDate.of(2023, 12, 20))
										.cupos(30)
										.descripcion("Curso donde se enseñará la creación, y mantención de bases de datos relacionales utilizando el lenguaje SQL y los servicios de Oracle.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/oracle.jpg")))
										.build();
				cRepo.save(cursoJava);
				cRepo.save(cursoSpring);
				cRepo.saveAndFlush(cursoOracle);
			}
		};
	}
	
	

}
