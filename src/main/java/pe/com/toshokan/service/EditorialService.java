package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.Editorial;

public interface EditorialService {
	public List<Editorial> listarEditoriales();
	
	public Editorial agregarEditorial(Editorial editorial);
	
	public Editorial buscarEditorialById(String id);
	
	public String eliminarEditorialById(String id);
	
	public Editorial actualizarEditorial(Editorial nuevo);
	
	// Métodos para procedimientos almacenados
    List<String> getEditorialColumns();
}
