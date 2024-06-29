package pe.com.toshokan.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import pe.com.toshokan.model.BoletaAlquiler;
import pe.com.toshokan.model.CarritoAlquiler;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.service.BoletaAlquilerService;
import pe.com.toshokan.service.CarritoDeAlquilerService;
import pe.com.toshokan.service.LibroService;

@RestController
@RequestMapping("/api/carritoalquiler")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoAlquilerController {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired(required = true)
	private CarritoDeAlquilerService service;

	@Autowired(required = true)
	private BoletaAlquilerService service_boleta;

	@Autowired(required = true)
	private LibroService service_libro;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<CarritoAlquiler>> listarCarritoAlquileres() {
		return ResponseEntity.ok(service.listarCarritoAlquiler());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarritoAlquiler> buscarCarritoAlquiler(@PathVariable int id) {
		return ResponseEntity.ok(service.buscarCarritoAlquilerById(id));
	}

	@GetMapping("/foruserandbook/{libroID}/{usuarioID}")
	public ResponseEntity<List<CarritoAlquiler>> buscarCarritoAlquilerPorLibroYUsuario(@PathVariable String libroID,
			@PathVariable String usuarioID) {
		Libro libro = new Libro();
		libro.setId(libroID);

		Usuario usuario = new Usuario();
		usuario.setId(usuarioID);

		return ResponseEntity.ok(service.buscarPorLibroYUsuario(libro, usuario));
	}

	@Transactional
	@GetMapping("/addToCarAlquiler/{usuarioID}/{libroID}")
	public ResponseEntity<Integer> agregarLibroAlCarritoDeAlquiler(@PathVariable String usuarioID,
			@PathVariable String libroID) {
		return ResponseEntity.ok(service.addToCartAlquiler(usuarioID, libroID));
	}

	@Transactional
	@GetMapping("/lsdatos/{usuarioID}")
	public ResponseEntity<List<CarritoAlquiler>> listarDatosEspecialesCarritoAlquiler(@PathVariable String usuarioID) {
		return ResponseEntity.ok(service.listarDatosCarritoAlquiler(usuarioID));
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<String>> getCarritoAlquilerColumns() {
		return ResponseEntity.ok(service.getRentcarColumns());
	}

	@Transactional
	@GetMapping("/lastcode")
	public ResponseEntity<List<CarritoAlquiler>> findLastCodeCarritoAlquiler() {
		return ResponseEntity.ok(service.findLastCodeRentCar());
	}

	@GetMapping("/constanciaregistroalquiler/{CodBol}")
	public ResponseEntity<byte[]> generarConstanciaAlquiler(@PathVariable String CodBol) {
		try {
			System.out.println("SE ESTA GENERANDO LA CONSTANCIA!-----------------------------");
			String ru = resourceLoader.getResource("classpath:reports/constancia_alquiler.jasper").getURI().getPath();
			HashMap<String, Object> parametros = new HashMap<>();
			parametros.put("identificador_boleta", CodBol);

			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] pdfData = outStream.toByteArray();

			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=constancia_alquiler.pdf");
			headers.setContentType(MediaType.APPLICATION_PDF);

			return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JRException | SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// PostMapping -> "guardar" -> grabar en el back lo enviado del front
	@PostMapping
	public ResponseEntity<CarritoAlquiler> agregarCarritoAlquiler(@Validated @RequestBody CarritoAlquiler nuevo) {
		// Agregar una nueva CarritoAlquiler
		CarritoAlquiler car = service.agregarCarritoAlquiler(nuevo);
		return new ResponseEntity<>(car, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCarritoAlquileresById(@PathVariable int id) {
		// return ResponseEntity.ok(service.eliminarCarritoAlquilerById(id));
		try {
			service.eliminarCarritoAlquilerById(id);
			return ResponseEntity.ok().build();

		} catch (EmptyResultDataAccessException e) {
			// Manejo de errores si el registro no existe
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			// Otro manejo de errores genérico
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/foruser/{id}")
	public ResponseEntity<String> eliminarCarritoAlquilerByUsuario(@PathVariable String id) {
		Usuario us = new Usuario();
		us.setId(id);
		return ResponseEntity.ok(service.eliminarCarritoAlquilerByUsuario(us));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<CarritoAlquiler> actualizarCarritoAlquilers(@PathVariable int id,
			@Validated @RequestBody CarritoAlquiler carritoAlquiler) {
		// actualizar un producto
		CarritoAlquiler car = service.buscarCarritoAlquilerById(id);
		car.setTituloLibro(carritoAlquiler.getTituloLibro());
		car.setStockDisponible(carritoAlquiler.getStockDisponible());
		car.setPrecioLibro(carritoAlquiler.getPrecioLibro());

		service.actualizarCarritoAlquiler(car);
		return new ResponseEntity<>(car, HttpStatus.CREATED);
	}

	@Transactional
	@PostMapping("/pagarcarrito/{userID}")
	public ResponseEntity<String> pagarCarritoAlquiler(@PathVariable String userID,
			@RequestBody List<Integer> numList) {

		ArrayList<Integer> cantList = new ArrayList<>(numList);
		String codigoBoleta = "";

		if (cantList == null || cantList.isEmpty()) {
			return ResponseEntity.badRequest().body("La lista de cantidades está vacía");
		} else {
			System.out.println("Control Nmr 1 ------------------------");
			boolean optional = false;
			int pos = 0;
			BoletaAlquiler estadoRegister = new BoletaAlquiler();

			List<CarritoAlquiler> listCarrito = service.listarDatosCarritoAlquiler(userID);

			System.out.println("Control Nmr 2 ------------------------");

			String detalles = "";

			for (CarritoAlquiler x : listCarrito) {
				if (!(x.getStockDisponible() >= 1)) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				} else {
					optional = true;
				}
				pos += 1;
			}

			System.out.println("Control Nmr 3 ------------------------");

			if (optional) {
				SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
				SimpleDateFormat formattRead = new SimpleDateFormat("YYYYMMdd");
				SimpleDateFormat hour = new SimpleDateFormat("HHmmss");
				pos = 0;
				Date fechaHoy = new Date();

				int validate = 0;
				codigoBoleta = "BOLLIBCODE";
				codigoBoleta += hour.format(fechaHoy) + formattRead.format(fechaHoy);

				System.out.println("Control Nmr 4 ------------------------");

				for (CarritoAlquiler x : listCarrito) {

					System.out.println("Control Nmr 4.1 ------------------------");

					validate = 0;
					String fechaVencimiento = SumarFecha(fechaHoy, cantList.get(pos));
					detalles += "Libro ---> titulo: " + x.getTituloLibro() + "\n";
					detalles += "codigo: " + x.getObjLibro().getId() + "\n";
					detalles += "precio: " + "S/." + x.getPrecioLibro() + " x Dia" + "\n";
					detalles += "dias alquilados: " + cantList.get(pos) + "\n";
					detalles += "fecha vencimiento: " + fechaVencimiento + "\n";
					detalles += "importe: " + cantList.get(pos) * x.getPrecioLibro() + "\n\n\n";

					int newStock = x.getStockDisponible() - 1;
					String id = x.getObjLibro().getId();

					detalles += "--------> Importe Total: " + cantList.get(pos) * x.getPrecioLibro();

					double precioDia = x.getObjLibro().getPrecio() * 0.2;
					double impTotal = cantList.get(pos) * (x.getPrecioLibro() * 0.2);
					int cantDias = cantList.get(pos);

					BoletaAlquiler rentBol = new BoletaAlquiler();
					rentBol.setCodigoBoleta(codigoBoleta);
					rentBol.setObjUsuario(x.getObjUsuario());
					rentBol.setObjLibro(x.getObjLibro());

					rentBol.setDetalleLibro(detalles);
					rentBol.setPrecioXDia(precioDia);
					rentBol.setImporteTotal(impTotal);
					rentBol.setFechaAlquiler(formatt.format(fechaHoy));
					rentBol.setDiasAlquilados(cantDias);
					rentBol.setFechaVencimiento(fechaVencimiento);
					rentBol.setTitulo(x.getObjLibro().getTitulo());

					try {
						System.out.println("Control Nmr 4.2 ------------------------");
						estadoRegister = service_boleta.agregarBoletaAlquiler(rentBol);
					} catch (Exception e) {
						System.out.println("Error al agregar a boleta (CarritoAlquilerController): " + e.getMessage());
					}

					pos++;
					detalles = "";
					impTotal = 0;

					System.out.println("Control Nmr 4.3 ------------------------");

					service_libro.descontarLibros(newStock, id);
					validate++;
				}

				System.out.println("Control Nmr 5 VALIDATE ------------------------");

				if (validate > 0) {
					Usuario user = new Usuario();
					user.setId(userID);
					try {
						String empty = service.eliminarCarritoAlquilerByUsuario(user);
						System.out.println("Control Nmr 6 EMPTY ------------------------");
					} catch (Exception e) {
						System.out.println("Error al vaciar carrito (CarritoAlquilerController): " + e.getMessage());
					}
				}
				System.out.println("Control Nmr 7 SUCCESS ------------------------");
				return new ResponseEntity<String>(codigoBoleta, HttpStatus.OK);
			}
			System.out.println("Control FINAL ------------------------");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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
