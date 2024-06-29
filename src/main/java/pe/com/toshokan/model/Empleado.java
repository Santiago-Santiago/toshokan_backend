package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_empleado")
public class Empleado {

	@Id
	@Column(name = "emp_id")
	private String id;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 70, message = "El nombre debe tener entre 3 y 70 caracteres")
	@Column(name = "emp_nom", length = 45)
	private String nombre;

	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 3, max = 70, message = "El nombre debe tener entre 3 y 70 caracteres")
	@Column(name = "emp_apell", length = 70)
	private String apellido;

	@NotBlank(message = "La fecha de nacimiento es obligatoria")
	@Column(name = "emp_fec_nac")
	private String fechaNacimiento;

	@NotBlank(message = "El DNI es obligatorio")
	@Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
	@Column(name = "emp_dni")
	private String DNI;

	@Column(name = "emp_fec_contr")
	private String fechaContrato;

	@NotBlank(message = "El numero celular es obligatorio")
	@Size(min = 9, max = 9, message = "El numero celular debe tener 9 digitos")
	@Column(name = "emp_celu", length = 9)
	private String celular;

	@NotBlank(message = "El Email es obligatorio")
	@Column(name = "emp_email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "emp_cargo")
	private Cargo objCargo;

	@NotBlank(message = "La clave de seguridad es obligatoria")
	@Column(name = "emp_password")
	private String password;

}
