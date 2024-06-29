package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_autor")
public class Autor {
	@Id
	@Column(name = "aut_id")
	private String id;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 70, message = "El nombre debe tener entre 3 y 70 caracteres")
	@Column(name = "aut_nom", length = 45)
	private String nombre;

	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 3, max = 70, message = "El apellido debe tener entre 3 y 70 caracteres")
	@Column(name = "aut_apell", length = 45)
	private String apellido;

	@NotBlank(message = "La fecha de nacimiento es obligatoria")
	@Size(min = 3, max = 70, message = "El apellido debe tener entre 3 y 70 caracteres")
	@Column(name = "aut_fec_nac", length = 45)
	private String fechaNacimiento;

	@NotBlank(message = "La fecha de muerte es obligatoria")
	@Size(min = 3, max = 70, message = "El apellido debe tener entre 3 y 70 caracteres")
	@Column(name = "aut_fec_muerte", length = 45)
	private String fechaMuerte;

	@NotBlank(message = "La nacionalidad es obligatorio")
	@Size(min = 3, max = 70, message = "El apellido debe tener entre 3 y 70 caracteres")
	@Column(name = "aut_nacionalidad", length = 45)
	private String nacionalidad;
}
