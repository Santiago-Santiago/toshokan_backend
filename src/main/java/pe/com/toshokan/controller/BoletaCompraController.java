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

import pe.com.toshokan.model.BoletaCompra;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.service.BoletaCompraService;

@RestController
@RequestMapping("/api/boletadecompras")
@CrossOrigin(origins = "http://localhost:4200")
public class BoletaCompraController {

	@Autowired(required = true)
	private BoletaCompraService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<BoletaCompra>> listarBoletaCompra() {
		return ResponseEntity.ok(service.listarBoletaCompra());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BoletaCompra> buscarBoletaCompra(@PathVariable int id) {
		return ResponseEntity.ok(service.buscarBoletaCompraById(id));
	}

	@GetMapping("/foruser/{id}")
	public ResponseEntity<List<BoletaCompra>> buscarBoletaCompraPorUsuario(@PathVariable String id) {
		Usuario user = new Usuario();
		user.setId(id);
		return ResponseEntity.ok(service.buscarByUsuario(user));
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<BoletaCompra> agregarBoletaCompra(@Validated @RequestBody BoletaCompra nuevo) {
		// Agregar una nueva BoletaCompra
		BoletaCompra bolCompra = service.agregarBoletaCompra(nuevo);
		return new ResponseEntity<>(bolCompra, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarBoletaCompra(@PathVariable int id) {
		return ResponseEntity.ok(service.eliminarBoletaCompraById(id));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<BoletaCompra> actualizarBoletaCompra(@PathVariable int id,
			@Validated @RequestBody BoletaCompra boletaCompra) {
		// actualizar un producto
		BoletaCompra bol = service.buscarBoletaCompraById(id);
		bol.setDetallesLibro(boletaCompra.getDetallesLibro());
		bol.setImporteTotal(boletaCompra.getImporteTotal());
		bol.setFechaCompra(boletaCompra.getFechaCompra());
		bol.setCantidad(boletaCompra.getCantidad());
		bol.setCodigoBoleta(boletaCompra.getCodigoBoleta());
		bol.setPrecio(boletaCompra.getPrecio());

		service.actualizarBoletaCompra(bol);
		return new ResponseEntity<>(bol, HttpStatus.CREATED);
	}

}
