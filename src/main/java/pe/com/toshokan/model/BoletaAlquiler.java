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
@Table(name = "tb_boleta_alquiler")
public class BoletaAlquiler {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bol_rent_id")
	private int id;
	
	@Column(name="bol_rent_det_lib", nullable = true, columnDefinition = "TEXT")
	private String detalleLibro;
	
	@Column(name="bol_rent_imp_total")
	private double importeTotal;
	
	@Column(name="bol_rent_fec_alquiler")
	private String fechaAlquiler;
	
	@Column(name="bol_rent_dias_alquilados")
	private int diasAlquilados;
	
	@Column(name="bol_rent_fec_vencimiento")
	private String fechaVencimiento;
	
	@Column(name="bol_rent_fec_entrega")
	private String fechaEntrega;
	
	@Column(name="bol_cod_bol")
	private String codigoBoleta;
	
	@Column(name="bol_rent_precio_x_dia")
	private double precioXDia;
	
	@Column(name="bol_rent_titulo")
	private String titulo;

	@Column(name="bol_rent_dias_retraso", nullable = true, columnDefinition = "INT DEFAULT '0'")
	private int diasRetraso;

	@Column(name="bol_rent_mora", nullable = true, columnDefinition = "DOUBLE DEFAULT '0'")
	private double mora;
	
	@OneToOne
	@JoinColumn(name = "bol_rent_user", referencedColumnName = "user_id", updatable = false)
	private Usuario objUsuario;
	
	@OneToOne
	@JoinColumn(name = "bol_rent_lib", referencedColumnName = "lib_id", updatable = false)
	private Libro objLibro;

}
