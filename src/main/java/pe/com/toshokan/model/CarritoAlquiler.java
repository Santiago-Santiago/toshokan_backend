package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_rentcar")
public class CarritoAlquiler {
	
	@Id
	@Column(name = "rentcar_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "rentcar_lib_titulo")
	private String tituloLibro;
	
	@Column(name = "rentcar_lib_stock_disp", nullable = true, updatable = false)
	private int stockDisponible;
	
	@Column(name = "rentcar_lib_precio", nullable = true, updatable = false)
	private double precioLibro;

	@OneToOne
	@JoinColumn(name = "rentcar_libro_id")
	private Libro objLibro;

	@OneToOne
	@JoinColumn(name = "rentcar_user_id")
	private Usuario objUsuario;

}
