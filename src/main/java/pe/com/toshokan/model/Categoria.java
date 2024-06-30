package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_categoria")
public class Categoria {
	@Id
	@Column(name = "cat_id")
	private String id;
	
	@Column(name = "cat_nom")
	private String nombre;


}
