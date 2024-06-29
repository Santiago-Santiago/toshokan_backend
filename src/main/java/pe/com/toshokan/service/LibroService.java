package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.Libro;
import pe.com.toshokan.repository.ILibroProjection;

public interface LibroService {

	public List<Libro> listarLibros();

	public List<Libro> listarLibrosPorTitulo(String consulta);

	public Libro agregarLibro(Libro nuevo);

	public Libro buscarLibrosById(String id);

	public List<Libro> buscarLibrosByTitulo(String titulo);

	public String eliminarLibrosById(String id);

	public Libro actualizarLibros(Libro libro);

	List<ILibroProjection> listarLibrosReducido();

	List<Libro> findByTitulo(String consulta);

	Integer descontarLibros(int newStock, String libroID);

	public Libro convertirBase64(Libro lib);

}
