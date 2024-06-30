package pe.com.toshokan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import pe.com.toshokan.model.Editorial;
import pe.com.toshokan.service.EditorialService;

@RestController
@RequestMapping("/api/editoriales")
@CrossOrigin(origins = "http://localhost:4200")
public class EditorialController {

	@Autowired(required = true)
	private EditorialService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<Editorial>> listarEditoriales() {
		return ResponseEntity.ok(service.listarEditoriales());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Editorial> buscarEditorial(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarEditorialById(id));
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<Editorial> agregarEditorial(@Validated @RequestBody Editorial nuevo) {
		// Agregar una nueva Editorial
		Editorial edit = service.agregarEditorial(nuevo);
		edit.setId("EDT");
		return new ResponseEntity<>(edit, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEditorialById(@PathVariable String id) {
		// return ResponseEntity.ok(service.eliminarCarritoAlquilerById(id));
		try {
			service.eliminarEditorialById(id);
			return ResponseEntity.ok().build();

		} catch (EmptyResultDataAccessException e) {
			// Manejo de errores si el registro no existe
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			// Otro manejo de errores genérico
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<Editorial> actualizarEditorials(@PathVariable String id,
			@Validated @RequestBody Editorial editorial) {
		// actualizar un producto
		Editorial c = service.buscarEditorialById(id);
		c.setNombre(editorial.getNombre());
		service.actualizarEditorial(c);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<String>> getEditorialColumns() {
		return ResponseEntity.ok(service.getEditorialColumns());
	}

}
