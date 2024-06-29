package pe.com.toshokan.service;

import java.util.List;
import pe.com.toshokan.model.CarritoCompra;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;

public interface CarritoDeCompraService {
	public List<CarritoCompra> listarCarritoCompra();

	public CarritoCompra agregarCarritoCompra(CarritoCompra nuevo);

	public CarritoCompra buscarCarritoCompraById(int id);

	public List<CarritoCompra> buscarPorLibroYUsuario(Libro libro, Usuario usuario);

	public String eliminarCarritoCompraById(int id);
	
	public String eliminarCarritoCompraByUsuario(Usuario usuario);

	public CarritoCompra actualizarCarritoCompra(CarritoCompra carritoAlquiler);
	
	// Métodos para procedimientos almacenados
    List<CarritoCompra> listarDatosCarrito(String usuarioID);
    
	Integer addToCart(String usuarioID, String libroID);
	
	List<CarritoCompra> getBuyCarColumns();
}
