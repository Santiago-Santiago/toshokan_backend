package pe.com.toshokan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import pe.com.toshokan.model.Empleado;
import pe.com.toshokan.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

	@Autowired(required = true)
	private EmpleadoService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<Empleado>> listarEmpleados() {
		return ResponseEntity.ok(service.listarEmpleados());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empleado> buscarEmpleados(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarEmpleadosById(id));
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<String>> getEmpleadoColumns() {
		return ResponseEntity.ok(service.getEmpleadoColumns());
	}

	@Transactional
	@GetMapping("/validaringreso/{code}/{pass}")
	public ResponseEntity<List<Empleado>> validarIngresoEmpleado(@PathVariable String code, @PathVariable String pass) {
		return ResponseEntity.ok(service.validarIngresoEmpleado(code, pass));
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<Empleado> agregarEmpleado(@Validated @RequestBody Empleado nuevo) {
		// Agregar una nueva Empleado
		Empleado emp = service.agregarEmpleado(nuevo);
		emp.setId("E");
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarEmpleados(@PathVariable String id) {
		return ResponseEntity.ok(service.eliminarEmpleadosById(id));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> actualizarEmpleados(@PathVariable String id,
			@Validated @RequestBody Empleado empleado) {
		// actualizar un producto
		Empleado emp = service.buscarEmpleadosById(id);
		emp.setNombre(empleado.getNombre());
		emp.setApellido(empleado.getApellido());
		emp.setFechaNacimiento(empleado.getFechaNacimiento());
		emp.setDNI(empleado.getDNI());
		emp.setFechaContrato(empleado.getFechaContrato());
		emp.setCelular(empleado.getCelular());
		emp.setEmail(empleado.getEmail());
		emp.setObjCargo(empleado.getObjCargo());

		service.actualizarEmpleados(emp);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

}
