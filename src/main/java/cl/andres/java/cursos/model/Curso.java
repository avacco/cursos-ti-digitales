package cl.andres.java.cursos.model;

import java.time.LocalDate;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 1, max = 50, message = "El nombre del curso debe tener entre 1 y 50 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	@NotNull(message = "Debe existir una fecha de inicio")
	@Future(message = "La fecha de inicio debe ser superior al presente")
	@Column(nullable = false)
	private LocalDate fechaInicio;
	
	@NotNull(message = "Debe existir una fecha de finalizacion")
	@Future(message = "La fecha de finalizacion debe ser superior al presente")
	@Column(nullable = false)
	private LocalDate fechaFin;
	
	@Min(value = 5, message = "Debe haber al menos 5 cupos disponibles")
	@Column(nullable = false)
	private int cupos;
	
	@Size(min = 1, max = 255, message = "La descripcion no debe exceder los 255 caracteres")
	@Column(nullable = false)
	private String descripcion;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] imagen;
	
	@OneToMany
	private Set<Estudiante> estudiantes;
	
	@Transient
	@AssertTrue(message = "La fecha de finalizacion debe ser superior a la fecha inicio")
	private boolean isFechaFinMayorQueFechaInicio() {
		if(fechaFin != null) {
			return fechaFin.isAfter(fechaInicio);
		}
		return false;
	}
	
}
