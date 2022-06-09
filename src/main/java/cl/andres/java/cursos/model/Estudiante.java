package cl.andres.java.cursos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String rut;
	
	@Size(min = 1, max = 30)
	@Column(nullable = false)
	private String nombre1;
	
	@Size(min = 1, max = 30)
	@Column(nullable = false)
	private String nombre2;
	
	@Size(min = 1, max = 30)
	@Column(nullable = false)
	private String apellidoPaterno;
	
	@Size(min = 1, max = 30)
	@Column(nullable = false)
	private String apellidoMaterno;
	
	@Size(min = 1, max = 30)
	@Column(nullable = false)
	private String email;
	
	@Size(min = 6)
	@Column(nullable = false)
	private String password;
	
	@Size(min = 6, max = 30)
	@Column(nullable = false)
	private String telefono;
	
	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String direccion;

	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String region;
	
	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String comuna;
	
	@ManyToOne
	private Curso curso;
	
}
