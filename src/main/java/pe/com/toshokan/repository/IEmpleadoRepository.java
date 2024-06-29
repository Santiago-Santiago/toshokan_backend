package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import pe.com.toshokan.model.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, String>{
	@Procedure(name = "validarIngresoEmpleado")
	List<Empleado> validarIngresoEmpleado(@Param("Usuario") String Usuario, @Param("pass") String pass);

	@Procedure(name = "getEmpleadoColumns")
	List<String> getEmpleadoColumns();
	
}
