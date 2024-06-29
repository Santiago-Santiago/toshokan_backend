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
import pe.com.toshokan.model.Cargo;
import pe.com.toshokan.service.CargoService;

@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "http://localhost:4200")
public class CargoController {

	@Autowired(required = true)
	private CargoService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<Cargo>> listarCargos() {
		return ResponseEntity.ok(service.listarCargos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cargo> buscarCargos(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarCargoById(id));
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<String>> getCargoColumns() {
		return ResponseEntity.ok(service.getCargoColumns());
	}

	@Transactional
	@GetMapping("/lastcode")
	public ResponseEntity<List<Cargo>> findLastCodeCargo() {
		return ResponseEntity.ok(service.findLastCodeCargo());
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<Cargo> agregarCargo(@Validated @RequestBody Cargo nuevo) {
		// Agregar una nueva Cargo
		Cargo cat = service.agregarCargo(nuevo);
		cat.setId("CAR");
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCargos(@PathVariable String id) {
		return ResponseEntity.ok(service.eliminarCargoById(id));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<Cargo> actualizarCargos(@PathVariable String id, @Validated @RequestBody Cargo cargo) {
		// actualizar un producto
		Cargo c = service.buscarCargoById(id);
		c.setNombre(cargo.getNombre());
		service.actualizarCargo(c);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}

}
