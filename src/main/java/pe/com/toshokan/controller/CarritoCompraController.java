package pe.com.toshokan.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import pe.com.toshokan.model.BoletaCompra;
import pe.com.toshokan.model.BoletaCompra;
import pe.com.toshokan.model.CarritoCompra;
import pe.com.toshokan.model.CarritoCompra;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.service.BoletaCompraService;
import pe.com.toshokan.service.CarritoDeCompraService;
import pe.com.toshokan.service.LibroService;

@RestController
@RequestMapping("/api/carritocompra")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoCompraController {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired(required = true)
	private CarritoDeCompraService service;

	@Autowired(required = true)
	private BoletaCompraService service_boleta;

	@Autowired(required = true)
	private LibroService service_libro;

	// GetMapping -> "cargar" -> listados o consultas
	@GetMapping
	public ResponseEntity<List<CarritoCompra>> listarCarritoCompra() {
		return ResponseEntity.ok(service.listarCarritoCompra());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarritoCompra> buscarCarritoCompra(@PathVariable int id) {
		return ResponseEntity.ok(service.buscarCarritoCompraById(id));
	}

	@GetMapping("foruserandbook/{libroID}/{usuarioID}")
	public ResponseEntity<List<CarritoCompra>> buscarCarritoCompraPorLibroYUsuario(@PathVariable String libroID,
			@PathVariable String usuarioID) {
		Libro libro = new Libro();
		libro.setId(libroID);

		Usuario usuario = new Usuario();
		usuario.setId(usuarioID);
		return ResponseEntity.ok(service.buscarPorLibroYUsuario(libro, usuario));
	}

	@Transactional
	@GetMapping("/addToCarCompra/{usuarioID}/{libroID}")
	public ResponseEntity<Integer> agregarLibroAlCarritoDeCompra(@PathVariable String usuarioID,
			@PathVariable String libroID) {
		return ResponseEntity.ok(service.addToCart(usuarioID, libroID));
	}

	@Transactional
	@GetMapping("/lsdatos/{usuarioID}")
	public ResponseEntity<List<CarritoCompra>> listarDatosEspecialesCarritoCompra(@PathVariable String usuarioID) {
		return ResponseEntity.ok(service.listarDatosCarrito(usuarioID));
	}

	@Transactional
	@GetMapping("/columns")
	public ResponseEntity<List<CarritoCompra>> getCarritoCompraColumns() {
		return ResponseEntity.ok(service.getBuyCarColumns());
	}

	@GetMapping("/constanciaregistrocompras/{CodBol}")
	public ResponseEntity<byte[]> generarConstanciaCompra(@PathVariable String CodBol) {
		try {
			System.out.println("SE ESTA GENERANDO LA CONSTANCIA!-----------------------------");
			String ru = resourceLoader.getResource("classpath:reports/constancia_compra.jasper").getURI().getPath();
			HashMap<String, Object> parametros = new HashMap<>();
			parametros.put("identificador_boleta", CodBol);

			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] pdfData = outStream.toByteArray();

			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=constancia_compra.pdf");
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
	public ResponseEntity<CarritoCompra> agregarCarritoCompra(@Validated @RequestBody CarritoCompra nuevo) {
		// Agregar una nueva CarritoCompra
		CarritoCompra car = service.agregarCarritoCompra(nuevo);
		return new ResponseEntity<>(car, HttpStatus.CREATED);
	}

	// DeleteMapping eliminar datos segun lo enviado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCarritoComprasById(@PathVariable int id) {
		try {
			service.eliminarCarritoCompraById(id);
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
	public ResponseEntity<String> eliminarCarritoCompraByUsuario(@PathVariable String id) {
		Usuario us = new Usuario();
		us.setId(id);
		return ResponseEntity.ok(service.eliminarCarritoCompraByUsuario(us));
	}

	// PutMapping -> modificar datos segun lo enviado
	@PutMapping("/{id}")
	public ResponseEntity<CarritoCompra> actualizarCarritoCompras(@PathVariable int id,
			@Validated @RequestBody CarritoCompra carritoCompra) {
		// actualizar un producto
		CarritoCompra car = service.buscarCarritoCompraById(id);
		car.setTituloLibro(carritoCompra.getTituloLibro());
		car.setStockDisponible(carritoCompra.getStockDisponible());
		car.setPrecio(carritoCompra.getPrecio());

		service.actualizarCarritoCompra(car);
		return new ResponseEntity<>(car, HttpStatus.CREATED);
	}

	@Transactional
	@PostMapping("/pagarcarrito/{userID}")
	public ResponseEntity<String> pagarCarritoCompra(@PathVariable String userID, @RequestBody List<Integer> numList) {

		ArrayList<Integer> cantList = new ArrayList<>(numList);
		String codigoBoleta = "";
		double importeTotal = 0;

		int newStock = 0;
		String id = "";
		int cantidad = 0;
		int cantidadTotal = 0;
		double precio = 0;
		String fec = "";

		Usuario usuarioOBJ = new Usuario();
		Libro libroOBJ = new Libro();

		if (cantList == null || cantList.isEmpty()) {
			return ResponseEntity.badRequest().body("La lista de cantidades está vacía");
		} else {
			System.out.println("Control Nmr 1 ------------------------");
			boolean optional = false;
			int pos = 0;
			BoletaCompra estadoRegister = new BoletaCompra();

			List<CarritoCompra> listCarrito = service.listarDatosCarrito(userID);

			System.out.println("Control Nmr 2 ------------------------");

			String detalles = "";

			for (CarritoCompra x : listCarrito) {
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

				for (CarritoCompra x : listCarrito) {

					System.out.println("Control Nmr 4.1 ------------------------");

					validate = 0;
					detalles += "Libro ---> titulo: " + x.getTituloLibro() + "\n";
					detalles += "codigo: " + x.getObjLibro().getId() + "\n";
					detalles += "precio: " + "S/." + x.getPrecio() + " x Dia" + "\n";
					detalles += "cantidad: " + cantList.get(pos) + "\n";
					detalles += "importe: " + cantList.get(pos) * x.getPrecio() + "\n\n\n";
					importeTotal += cantList.get(pos) * x.getPrecio();

					newStock = x.getStockDisponible() - 1;
					id = x.getObjLibro().getId();
					cantidad = cantList.get(pos);
					cantidadTotal += cantidad;
					precio = x.getPrecio();
					fec = formatt.format(fechaHoy);
					usuarioOBJ = x.getObjUsuario();
					libroOBJ = x.getObjLibro();

					BoletaCompra buyBol = new BoletaCompra();
					buyBol.setCodigoBoleta(codigoBoleta);
					buyBol.setObjUsuario(usuarioOBJ);
					buyBol.setObjLibro(libroOBJ);

					buyBol.setDetallesLibro(detalles);
					buyBol.setCantidad(cantidad);
					buyBol.setImporteTotal(cantList.get(pos) * x.getPrecio());
					buyBol.setPrecio(precio);
					buyBol.setFechaCompra(formatt.format(fechaHoy));

					detalles += "--------> Importe Total: " + importeTotal;

					pos++;
					service_libro.descontarLibros(newStock, id);
					validate++;

					try {
						System.out.println("Control Nmr 4.2 ------------------------");
						estadoRegister = service_boleta.agregarBoletaCompra(buyBol);
					} catch (Exception e) {
						System.out.println("Error al agregar a boleta (CarritoCompraController): " + e.getMessage());
					}

					importeTotal = 0;
				}

				System.out.println("Control Nmr 4.3 ------------------------");

				System.out.println("Control Nmr 5 VALIDATE ------------------------");

				if (validate > 0) {
					Usuario user = new Usuario();
					user.setId(userID);
					try {
						String empty = service.eliminarCarritoCompraByUsuario(user);
						System.out.println("Control Nmr 6 EMPTY ------------------------");
					} catch (Exception e) {
						System.out.println("Error al vaciar carrito (CarritoCompraController): " + e.getMessage());
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
