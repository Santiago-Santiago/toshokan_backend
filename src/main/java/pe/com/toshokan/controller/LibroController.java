package pe.com.toshokan.controller;

import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.toshokan.model.CarritoAlquiler;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.repository.ILibroProjection;
import pe.com.toshokan.service.LibroService;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

	@Autowired(required = true)
	private LibroService service;

	@GetMapping
	public List<Libro> listarLibros() {
		List<Libro> lista = service.listarLibros();
		for (Libro lib : lista) {
			lib = service.convertirBase64(lib);
		}
		return lista;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Libro> buscarLibro(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarLibrosById(id));
	}

	@GetMapping("/portitulo/{titulo}")
	public List<Libro> listarPorTitulo(@PathVariable String titulo) {
		List<Libro> lista = service.buscarLibrosByTitulo(titulo);
		for (Libro lib : lista) {
			lib = service.convertirBase64(lib);
		}
		return lista;
	}

	@GetMapping("/searchbar/{consulta}")
	public List<Libro> listarPorTituloLIKE(@PathVariable String consulta) {
		List<Libro> lista = service.listarLibrosPorTitulo(consulta);
		for (Libro lib : lista) {
			lib = service.convertirBase64(lib);
		}
		return lista;
	}

	@GetMapping("/red")
	public List<ILibroProjection> listarLibrosReducido() {
		return service.listarLibrosReducido();
	}

	@GetMapping("/{id}/imagen")
	public ResponseEntity<String> getLibroImagen(@PathVariable String id) {
		Libro libro = service.buscarLibrosById(id);
		byte[] imgBlob = libro.getImgBlob();
		String base64Image = Base64.getEncoder().encodeToString(imgBlob);
		return ResponseEntity.ok(base64Image);
	}

	@PostMapping
	public ResponseEntity<Libro> agregarLibro(@Validated @RequestBody Libro nuevo) {
		// Agregar una nueva CarritoAlquiler
		Libro lib = service.agregarLibro(nuevo);
		lib.setId("LIB");
		return new ResponseEntity<>(lib, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarLibroById(@PathVariable String id) {
		// return ResponseEntity.ok(service.eliminarCarritoAlquilerById(id));
		try {
			service.eliminarLibrosById(id);
			return ResponseEntity.ok().build();

		} catch (EmptyResultDataAccessException e) {
			// Manejo de errores si el registro no existe
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			// Otro manejo de errores genérico
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
