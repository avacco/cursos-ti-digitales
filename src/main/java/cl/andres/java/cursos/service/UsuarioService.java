package cl.andres.java.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.andres.java.cursos.model.Usuario;
import cl.andres.java.cursos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public Usuario crearUsuario(Usuario usuario) {
		String passCodificado = encoder.encode(usuario.getPassword());
		usuario.setPassword(passCodificado);
		return usuarioRepository.save(usuario);
	}
	
	public long contarUsuarios() {
		return usuarioRepository.count();
	}
	
}
