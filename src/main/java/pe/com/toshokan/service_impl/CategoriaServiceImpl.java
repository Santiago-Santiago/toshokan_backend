package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Categoria;
import pe.com.toshokan.repository.ICategoriaRepository;
import pe.com.toshokan.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	ICategoriaRepository repo;

	@Override
	public List<Categoria> listarCategorias() {
		return repo.findAll();
	}

	@Override
	public Categoria agregarCategoria(Categoria nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public Categoria buscarCategoriaById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String eliminarCategoriaById(String id) {
		repo.deleteById(id);
		return "Categoria Eliminada con Exito";
	}

	@Override
	public Categoria actualizarCategoria(Categoria categoria) {
		return repo.save(categoria);
	}

	@Override
	public List<String> getCategoriaColumns() {
		return repo.getCategoriaColumns();
	}

}
