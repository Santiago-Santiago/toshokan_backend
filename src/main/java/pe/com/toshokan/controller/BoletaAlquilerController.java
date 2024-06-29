package pe.com.toshokan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.toshokan.model.BoletaAlquiler;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.service.BoletaAlquilerService;

@RestController
@RequestMapping("/api/boletadealquileres")
@CrossOrigin(origins = "http://localhost:4200")
public class BoletaAlquilerController {

	@Autowired(required = true)
	private BoletaAlquilerService service;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<BoletaAlquiler>> listarBoletaAlquiler() {
		return ResponseEntity.ok(service.listarBoletaAlquiler());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BoletaAlquiler> buscarBoletaAlquiler(@PathVariable int id) {
		return ResponseEntity.ok(service.buscarBoletaAlquilerById(id));
	}

	@GetMapping("/foruser/{id}")
	public ResponseEntity<List<BoletaAlquiler>> buscarBoletaAlquilerPorUsuario(@PathVariable String id) {
		Usuario user = new Usuario();
		user.setId(id);
		return ResponseEntity.ok(service.buscarByUsuario(user));
	}

	@Transactional
	@GetMapping("/listtoactiverent/{id}")
	public ResponseEntity<List<BoletaAlquiler>> ListToActiveRent(@PathVariable String id) {
		Usuario user = new Usuario();
		user.setId(id);
		List<BoletaAlquiler> listadoBoletaAlquiler = service.buscarByUsuario(user);

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

		for (BoletaAlquiler x : listadoBoletaAlquiler) {
			Libro lib = x.getObjLibro();
			String titulo = lib.getTitulo();
			int diasRetraso = 0;
			x.setTitulo(titulo);
			try {
				diasRetraso = (int) (restarFecha(sdf.format(new Date()), x.getFechaVencimiento()));
				x.setDiasRetraso(diasRetraso);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("\n\n\n\nEl error es" + e.getMessage() + "\n\n\n");
			}
			x.setFechaEntrega(x.getFechaEntrega() == null || x.getFechaEntrega().equals("NO ENTREGADO") ? "NO ENTREGADO"
					: x.getFechaEntrega());

			x.setMora(x.getDiasRetraso() * (lib.getPrecio() * 0.30));
		}
		return ResponseEntity.ok(listadoBoletaAlquiler);
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<BoletaAlquiler> agregarBoletaAlquiler(@Validated @RequestBody BoletaAlquiler nuevo) {
		// Agregar una nueva BoletaAlquiler
		BoletaAlquiler bolAlquiler = service.agregarBoletaAlquiler(nuevo);
		return new ResponseEntity<>(bolAlquiler, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarBoletaAlquiler(@PathVariable int id) {
		return ResponseEntity.ok(service.eliminarBoletaAlquilerById(id));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<BoletaAlquiler> actualizarBoletaAlquiler(@PathVariable int id,
			@Validated @RequestBody BoletaAlquiler boletaAlquiler) {
		// actualizar un producto
		BoletaAlquiler bol = service.buscarBoletaAlquilerById(id);
		bol.setDetalleLibro(boletaAlquiler.getDetalleLibro());
		bol.setImporteTotal(boletaAlquiler.getImporteTotal());
		bol.setFechaAlquiler(boletaAlquiler.getFechaAlquiler());
		bol.setDiasAlquilados(boletaAlquiler.getDiasAlquilados());
		bol.setFechaVencimiento(boletaAlquiler.getFechaVencimiento());
		bol.setFechaEntrega(boletaAlquiler.getFechaEntrega());
		bol.setCodigoBoleta(boletaAlquiler.getCodigoBoleta());
		bol.setPrecioXDia(boletaAlquiler.getPrecioXDia());
		bol.setTitulo(boletaAlquiler.getTitulo());
		bol.setDiasRetraso(boletaAlquiler.getDiasRetraso());
		bol.setMora(boletaAlquiler.getMora());

		service.actualizarBoletaAlquiler(bol);
		return new ResponseEntity<>(bol, HttpStatus.CREATED);
	}

	public static long restarFecha(String fechaActual, String fechaVencimiento) throws ParseException {
		LocalDate d1 = LocalDate.parse(fechaActual, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate d2 = LocalDate.parse(fechaVencimiento, DateTimeFormatter.ISO_LOCAL_DATE);

		Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());

		long diffDays = diff.toDays();

		if (diffDays < 0) {
			diffDays = diffDays * -1;

		} else {
			diffDays = 0;
		}

		return diffDays;
	}

	public String SumarFecha(java.util.Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		java.util.Date fechaCalendar = calendar.getTime();
		String fechaString = sdf.format(fechaCalendar);
		java.sql.Date fechaDate = java.sql.Date.valueOf(fechaString);
		return fechaDate.toString();
	}

}
