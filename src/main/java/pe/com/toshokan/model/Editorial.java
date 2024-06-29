package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_editorial")
public class Editorial {
	@Id
	@Column(name = "edit_id")
	private String id;

	@Column(name = "edit_nom")
	private String nombre;
}
