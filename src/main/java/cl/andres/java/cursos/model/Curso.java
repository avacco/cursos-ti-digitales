package cl.andres.java.cursos.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String nombre;
	
	@NotNull
	@Future
	@Column(nullable = false)
	private LocalDate fechaInicio;
	
	@NotNull
	@Future
	@Column(nullable = false)
	private LocalDate fechaFin;
	
	@NotNull
	@Column(nullable = false)
	private int cupos;
	
	@NotNull
	@Column(nullable = false)
	private String descripcion;
	
	private byte[] imagen;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Estudiante> estudiantes;
	
	@Transient
	@AssertTrue(message = "Campo 'fechaFin' debe ser una fecha posterior a 'fechaInicio'")
	private boolean isFechaFinMayorQueFechaInicio() {
		return fechaFin.isAfter(fechaInicio);
	}
	
}
