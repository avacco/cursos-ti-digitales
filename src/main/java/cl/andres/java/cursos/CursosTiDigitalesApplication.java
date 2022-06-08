package cl.andres.java.cursos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.andres.java.cursos.model.Administrador;
import cl.andres.java.cursos.model.Usuario;
import cl.andres.java.cursos.service.AdministradorService;
import cl.andres.java.cursos.service.UsuarioService;

@SpringBootApplication
public class CursosTiDigitalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosTiDigitalesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner datosIniciales(AdministradorService aService, UsuarioService uService) {
		return args -> {
			if(aService.contarAdmin() == 0) {
				Administrador admin = Administrador.builder()
													.username("admin")
													.password("1234")
													.build();
				aService.crearAdmin(admin);
														
			}
			
			if(uService.contarUsuarios() == 0) {
				Usuario usuario = Usuario.builder()
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
											.build()
									;
				uService.crearUsuario(usuario);
			}
		};
	}
	
	

}
