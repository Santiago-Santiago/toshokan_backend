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
@Table(name = "tb_buycar")
public class CarritoCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buycar_id")
	private int id;
	
	@Column(name = "buycar_lib_titulo")
	private String tituloLibro;

	@Column(name = "buycar_lib_stock_disp", nullable = true, updatable = false)
	private int stockDisponible;

	@Column(name = "buycar_lib_precio", nullable = true, updatable = false)
	private double precio;
	
	@OneToOne
	@JoinColumn(name = "buycar_user_id", updatable = false)
	private Usuario objUsuario;

	@OneToOne
	@JoinColumn(name = "buycar_libro_id", updatable = false)
	private Libro objLibro;

}
