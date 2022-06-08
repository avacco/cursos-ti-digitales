package cl.andres.java.cursos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.andres.java.cursos.model.Administrador;
import cl.andres.java.cursos.model.Usuario;

public class UserSeguridad implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Administrador administrador;
	
	public UserSeguridad(Usuario usuario, Administrador administrador) { this.usuario = usuario; this.administrador = administrador; }
	

	@Override
	public String getUsername() {
		if(usuario != null) return usuario.getRut();
		if(administrador != null) return administrador.getUsername();
		return null;
	}

	@Override
	public String getPassword() {
		if(usuario != null) return usuario.getPassword();
		if(administrador != null) return administrador.getPassword();
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(usuario != null) return List.of(new SimpleGrantedAuthority("ESTUDIANTE"));
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


	public Usuario getUsuario() {
		return usuario;
	}


	public Administrador getAdministrador() {
		return administrador;
	}

}
