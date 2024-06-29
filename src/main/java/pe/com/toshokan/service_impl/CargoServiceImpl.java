package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Cargo;
import pe.com.toshokan.repository.ICargoRepository;
import pe.com.toshokan.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	ICargoRepository repo;

	@Override
	public List<Cargo> listarCargos() {
		return repo.findAll();
	}

	@Override
	public Cargo agregarCargo(Cargo nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public Cargo buscarCargoById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String eliminarCargoById(String id) {
		repo.deleteById(id);
		return "Cargo Eliminado Correctamente";
	}

	@Override
	public Cargo actualizarCargo(Cargo cargo) {
		return repo.save(cargo);
	}

	@Override
	public List<Cargo> findLastCodeCargo() {
		return repo.findLastCodeCargo();
	}

	@Override
	public List<String> getCargoColumns() {
		return repo.getCargoColumns();
	}

}
