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
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired(required = true)
	private UsuarioService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return ResponseEntity.ok(service.listarUsuarios());
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<Usuario> agregarUsuario(@Validated @RequestBody Usuario nuevo) {
		// Agregar una nueva Usuario
		Usuario user = service.agregarUsuario(nuevo);
		user.setId("U");
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuarios(@PathVariable String id) {
		return ResponseEntity.ok(service.buscarUsuariosById(id));
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarUsuarios(@PathVariable String id) {
		return ResponseEntity.ok(service.eliminarUsuariosById(id));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuarios(@PathVariable String id,
			@Validated @RequestBody Usuario usuario) {
		// actualizar un producto
		Usuario us = service.buscarUsuariosById(id);
		us.setNombre(usuario.getNombre());
		us.setApellido(usuario.getApellido());
		us.setCelular(usuario.getCelular());
		us.setEmail(usuario.getEmail());
		us.setUsername(usuario.getUsername());

		service.actualizarUsuarios(us);
		return new ResponseEntity<>(us, HttpStatus.CREATED);
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<String>> getUsuarioColumns() {
		return ResponseEntity.ok(service.getUserColumns());
	}

	@Transactional
	@GetMapping("/validaringreso/{user}/{pass}")
	public ResponseEntity<List<Usuario>> validarIngresoUsuario(@PathVariable String user, @PathVariable String pass) {
		return ResponseEntity.ok(service.validarIngresoUsuario(user, pass));
	}

}
