package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_cargo")
public class Cargo {
	@Id
	@Column(name = "car_id")
	private String id;

	@Column(name = "car_nom")
	private String nombre;
}
