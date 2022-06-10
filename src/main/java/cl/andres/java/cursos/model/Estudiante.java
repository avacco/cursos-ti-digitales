package cl.andres.java.cursos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

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
	
	@Size(min = 10, max = 10, message = "El rut debe ser escrito en formato '12345678-9'")
	@Column(nullable = false, unique = true, length = 10)
	private String rut;
	
	@Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
	@Column(nullable = false)
	private String nombre1;
	
	@Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
	@Column(nullable = false)
	private String nombre2;
	
	@Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50 caracteres")
	@Column(nullable = false)
	private String apellidoPaterno;
	
	@Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50 caracteres")
	@Column(nullable = false)
	private String apellidoMaterno;
	
	@Size(min = 1, max = 250, message = "El correo debe tener entre 1 y 250 caracteres")
	@Column(nullable = false)
	private String email;
	
	@Size(min = 6, message = "La contraseña debe tener un minimo de 6 caracteres")
	@Column(nullable = false)
	private String password;
	
	@Size(min = 6, max = 30, message = "El telefono debe tener entre 6 y 30 caracteres")
	@Column(nullable = false)
	private String telefono;
	
	@Size(min = 1, max = 100,  message = "La direccion debe tener entre 1 y 100 caracteres")
	@Column(nullable = false)
	private String direccion;

	@Size(min = 1, max = 100)
	@Column(nullable = false)
	private String region;
	
	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String comuna;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] imagen;
	
	@ManyToOne
	private Curso curso;
	
}
