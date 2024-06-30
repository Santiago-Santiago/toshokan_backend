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

import pe.com.toshokan.model.Categoria;
import pe.com.toshokan.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

	@Autowired(required = true)
	private CategoriaService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		return ResponseEntity.ok(service.listarCategorias());
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<Categoria> agregarCategoria(@Validated @RequestBody Categoria nuevo) {
		// Agregar una nueva Categoria
		Categoria cat = service.agregarCategoria(nuevo);
		cat.setId("CAT"); // Asignar un ID fijo en este caso para pruebas, en producción debe ser generado automáticamente.
		return new ResponseEntity<Categoria>(cat, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategorias(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarCategoriaById(id));
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCategoriaById(@PathVariable String id) {
		// return ResponseEntity.ok(service.eliminarCarritoAlquilerById(id));
		try {
			service.eliminarCategoriaById(id);
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
	public ResponseEntity<Categoria> actualizarCategorias(@PathVariable String id,
			@Validated @RequestBody Categoria categoria) {
		// actualizar un producto
		Categoria c = service.buscarCategoriaById(id);
		c.setNombre(categoria.getNombre());
		service.actualizarCategoria(c);

		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}

	@GetMapping("/columns")
	public ResponseEntity<List<String>> getCategoriaColumns() {
		return ResponseEntity.ok(service.getCategoriaColumns());
	}

}
