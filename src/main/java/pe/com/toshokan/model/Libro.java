package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_libro")
public class Libro {
	@Id
	@Column(name = "lib_id")
	private String id;

	@Column(name = "lib_titulo")
	private String titulo;

	@Column(name = "lib_desc")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "lib_autor")
	private Autor objAutor;

	@Column(name = "lib_public")
	private String fechaPublicacion;

	@ManyToOne
	@JoinColumn(name = "lib_categoria")
	private Categoria objCategoria;

	@ManyToOne
	@JoinColumn(name = "lib_editorial")
	private Editorial objEditorial;

	@Column(name = "lib_pag")
	private int paginas;

	@Column(name = "lib_stock_disp")
	private int stockDisponible;

	@Column(name = "lib_stock_en_alqui")
	private int stockEnAlquiler;

	@Column(name = "lib_precio")
	private double precio;

	@Column(name = "lib_estado")
	private String estado;

	@Lob
	@Column(name = "lib_img")
	private byte[] imgBlob;

	@Transient
	private String imgPath;

	@Transient
	private String img64;

}
