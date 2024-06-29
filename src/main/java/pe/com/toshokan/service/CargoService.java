package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.Cargo;

public interface CargoService {
	public List<Cargo> listarCargos();
	
	public Cargo agregarCargo(Cargo nuevo);
	
	public Cargo buscarCargoById(String id);
	
	public String eliminarCargoById(String id);
	
	public Cargo actualizarCargo(Cargo cargo);
	
	// Métodos para procedimientos almacenados
    List<Cargo> findLastCodeCargo();

    List<String> getCargoColumns();
}
