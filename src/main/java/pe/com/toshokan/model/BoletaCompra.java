package pe.com.toshokan.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_boleta_compra")
public class BoletaCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="bol_buy_det_lib", nullable = true, columnDefinition = "TEXT")
	private String detallesLibro;
	
	@Column(name="bol_buy_imp_total", nullable = true)
	private double importeTotal;
	
	@Column(name="bol_buy_fec_compra")
	private String fechaCompra;

	@Column(name="bol_buy_cantidad")
	private int cantidad;
	
	@Column(name="bol_cod_bol")
	private String codigoBoleta;
	
	@Column(name="bol_buy_precio")
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="bol_buy_lib_id", referencedColumnName = "lib_id")
	private Libro objLibro;
	
	@ManyToOne
	@JoinColumn(name = "bol_buy_user", referencedColumnName = "user_id")
	private Usuario objUsuario;
}
