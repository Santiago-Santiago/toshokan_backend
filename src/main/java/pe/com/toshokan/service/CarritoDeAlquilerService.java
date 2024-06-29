package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.CarritoAlquiler;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;

public interface CarritoDeAlquilerService {

	public List<CarritoAlquiler> listarCarritoAlquiler();

	public CarritoAlquiler agregarCarritoAlquiler(CarritoAlquiler nuevo);

	public CarritoAlquiler buscarCarritoAlquilerById(int id);

	public List<CarritoAlquiler> buscarPorLibroYUsuario(Libro libro, Usuario usuario);

	public String eliminarCarritoAlquilerById(int id);

	public String eliminarCarritoAlquilerByUsuario(Usuario usuario);

	public CarritoAlquiler actualizarCarritoAlquiler(CarritoAlquiler carritoAlquiler);

	// Métodos para procedimientos almacenados
	Integer addToCartAlquiler(String usuarioID, String libroID);

	List<CarritoAlquiler> listarDatosCarritoAlquiler(String usuarioID);

	List<CarritoAlquiler> findLastCodeRentCar();

	List<String> getRentcarColumns();

}
