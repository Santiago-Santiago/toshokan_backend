package pe.com.toshokan.service_impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Libro;
import pe.com.toshokan.repository.ILibroProjection;
import pe.com.toshokan.repository.ILibroRepository;
import pe.com.toshokan.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	ILibroRepository repo;

	@Override
	public List<Libro> listarLibros() {
		return repo.findAll();
	}

	@Override
	public Libro agregarLibro(Libro nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public Libro buscarLibrosById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String eliminarLibrosById(String id) {
		repo.deleteById(id);
		return "Libro Eliminado con Exito";
	}

	@Override
	public Libro actualizarLibros(Libro libro) {
		return repo.save(libro);
	}

	@Override
	public List<ILibroProjection> listarLibrosReducido() {
		return repo.findBy();
	}

	@Override
	public List<Libro> findByTitulo(String consulta) {
		return repo.findByTituloContaining(consulta);
	}

	@Override
	public Integer descontarLibros(int newStock, String libroID) {
		return repo.descontarLibros(newStock, libroID);
	}

	@Override
	public Libro convertirBase64(Libro lib) {
		if (lib.getImgBlob() != null && lib.getImgBlob().length > 0) {
			String imgBase64 = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(lib.getImgBlob());
			lib.setImg64(imgBase64);
		}
		return lib;
	}

	@Override
	public List<Libro> listarLibrosPorTitulo(String consulta) {
		return repo.findByTituloContaining(consulta);
	}

	@Override
	public List<Libro> buscarLibrosByTitulo(String titulo) {
		return repo.findByTitulo(titulo);
	}

}
