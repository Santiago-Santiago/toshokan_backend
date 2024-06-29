package pe.com.toshokan.service;

import java.util.List;
import pe.com.toshokan.model.Empleado;

public interface EmpleadoService {

	public List<Empleado> listarEmpleados();

	public Empleado agregarEmpleado(Empleado nuevo);

	public Empleado buscarEmpleadosById(String id);

	public String eliminarEmpleadosById(String id);

	public Empleado actualizarEmpleados(Empleado empleado);

	// Métodos para procedimientos almacenados
	List<Empleado> validarIngresoEmpleado(String empleadoID, String pass);

	List<String> getEmpleadoColumns();
}
