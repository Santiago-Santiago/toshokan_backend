package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Editorial;
import pe.com.toshokan.repository.IEditorialRepository;
import pe.com.toshokan.service.EditorialService;

@Service
public class EditorialServiceImpl implements EditorialService{

	@Autowired
	IEditorialRepository repo;
	
	@Override
	public List<Editorial> listarEditoriales() {		
		return repo.findAll();
	}

	@Override
	public Editorial agregarEditorial(Editorial nuevo) {		
		return repo.save(nuevo);
	}

	@Override
	public Editorial buscarEditorialById(String id) {		
		return repo.findById(id).get();
	}

	@Override
	public String eliminarEditorialById(String id) {	
		repo.deleteById(id);
		return "Editorial Eliminada con Exito";
	}

	@Override
	public Editorial actualizarEditorial(Editorial editorial) {	
		return repo.save(editorial);
	}

	@Override
	public List<String> getEditorialColumns() {	
		return repo.getEditorialColumns();
	}

}
