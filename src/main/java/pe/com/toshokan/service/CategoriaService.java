package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.Categoria;

public interface CategoriaService {
	public List<Categoria> listarCategorias();
	
	public Categoria agregarCategoria(Categoria nuevo);
	
	public Categoria buscarCategoriaById(String id);
	
	public String eliminarCategoriaById(String id);
	
	public Categoria actualizarCategoria(Categoria categoria);
	
	// Métodos para procedimientos almacenados
    List<String> getCategoriaColumns();
}
