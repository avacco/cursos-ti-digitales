package cl.andres.java.cursos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.andres.java.cursos.model.Administrador;
import cl.andres.java.cursos.model.Estudiante;

public class UserSeguridad implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estudiante estudiante;
	private Administrador administrador;
	
	public UserSeguridad(Estudiante estudiante, Administrador administrador) { this.estudiante = estudiante; this.administrador = administrador; }
	

	@Override
	public String getUsername() {
		if(estudiante != null) return estudiante.getRut();
		if(administrador != null) return administrador.getUsername();
		return null;
	}

	@Override
	public String getPassword() {
		if(estudiante != null) return estudiante.getPassword();
		if(administrador != null) return administrador.getPassword();
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(estudiante != null) return List.of(new SimpleGrantedAuthority("ESTUDIANTE"));
		if(administrador != null) return List.of(new SimpleGrantedAuthority("ADMINISTRADOR"));
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public Estudiante getUsuario() {
		return estudiante;
	}


	public Administrador getAdministrador() {
		return administrador;
	}

}
