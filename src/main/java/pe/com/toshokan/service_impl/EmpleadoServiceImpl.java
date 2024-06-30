package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Empleado;
import pe.com.toshokan.repository.ICargoRepository;
import pe.com.toshokan.repository.IEmpleadoRepository;
import pe.com.toshokan.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	IEmpleadoRepository repo;

	@Autowired
	ICargoRepository repoCargo;

	@Override
	public List<Empleado> listarEmpleados() {
		return repo.findAll();
	}

	@Override
	public Empleado agregarEmpleado(Empleado nuevo) {
		//String id = nuevo.getObjCargo().getId();
		//Cargo cargo = repoCargo.findById(id).get();
		//nuevo.setObjCargo(cargo);
		return repo.save(nuevo);
	}

	@Override
	public Empleado buscarEmpleadosById(String id) {
		return repo.findById(id).get();
	}

	@Override
	public String eliminarEmpleadosById(String id) {
		repo.deleteById(id);
		return "Empleado Eliminado Exitosamente";
	}

	@Override
	public Empleado actualizarEmpleados(Empleado empleado) {
		return repo.save(empleado);
	}

	@Override
	public List<Empleado> validarIngresoEmpleado(String empleadoID, String pass) {
		return repo.validarIngresoEmpleado(empleadoID, pass);
	}

	@Override
	public List<String> getEmpleadoColumns() {
		return repo.getEmpleadoColumns();
	}

}
