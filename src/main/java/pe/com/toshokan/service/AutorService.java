package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.Autor;

public interface AutorService {

	public List<Autor> listarAutores();
	
	public Autor agregarAutor(Autor nuevo);
	
	public Autor buscarAutoresById(String id);
	
	public String eliminarAutoresById(String id);
	
	public Autor actualizarAutores(Autor autor);
	
	// Métodos para procedimientos almacenados
    List<Autor> findLastCodeAutor();

    List<String> getAutorColumns();
}
