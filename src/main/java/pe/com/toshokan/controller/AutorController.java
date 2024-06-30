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
import pe.com.toshokan.model.Autor;
import pe.com.toshokan.service.AutorService;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "http://localhost:4200")
public class AutorController {

	@Autowired(required = true)
	private AutorService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<Autor>> listarAutores() {
		return ResponseEntity.ok(service.listarAutores());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscarAutores(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarAutoresById(id));
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<String>> getAutorColumns() {
		return ResponseEntity.ok(service.getAutorColumns());
	}

	@Transactional
	@GetMapping("/lastcode")
	public ResponseEntity<List<Autor>> findLastCodeAutor() {
		return ResponseEntity.ok(service.findLastCodeAutor());
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<Autor> agregarAutor(@RequestBody Autor nuevo) {
		// Agregar una nueva Autor
		Autor aut = service.agregarAutor(nuevo);
		aut.setId("AUT");
		return new ResponseEntity<>(aut, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarAutorById(@PathVariable String id) {
		// return ResponseEntity.ok(service.eliminarCarritoAlquilerById(id));
		try {
			service.eliminarAutoresById(id);
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
	public ResponseEntity<Autor> actualizarAutors(@PathVariable String id, @Validated @RequestBody Autor autor) {
		// actualizar un producto
		Autor a = service.buscarAutoresById(id);
		a.setNombre(autor.getNombre());
		a.setApellido(autor.getApellido());
		a.setFechaNacimiento(autor.getFechaNacimiento());
		a.setFechaMuerte(autor.getFechaMuerte());
		a.setNacionalidad(autor.getNacionalidad());

		service.actualizarAutores(a);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

}
