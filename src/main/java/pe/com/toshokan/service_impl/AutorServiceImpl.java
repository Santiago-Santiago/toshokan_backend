package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Autor;
import pe.com.toshokan.repository.IAutorRepository;
import pe.com.toshokan.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	IAutorRepository repo;

	@Override
	public List<Autor> listarAutores() {
		return repo.findAll();
	}

	@Override
	public Autor agregarAutor(Autor autor) {
		return repo.save(autor);
	}

	@Override
	public Autor actualizarAutores(Autor nuevo) {
		return repo.save(nuevo);
	}

	// Implementación de los métodos para procedimientos almacenados
	@Override
	public List<Autor> findLastCodeAutor() {
		return repo.findLastCodeAutor();
	}

	@Override
	public List<String> getAutorColumns() {
		return repo.getAutorColumns();
	}

	@Override
	public Autor buscarAutoresById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String eliminarAutoresById(String id) {
		repo.deleteById(id);
		return "Autor Eliminado Exitosamente";
	}
}
