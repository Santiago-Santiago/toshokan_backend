package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.CarritoCompra;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.repository.ICarritoDeCompraRepository;
import pe.com.toshokan.service.CarritoDeCompraService;

@Service
public class CarritoDeCompraServiceImpl implements CarritoDeCompraService{

	@Autowired
	ICarritoDeCompraRepository repo;
	
	@Override
	public List<CarritoCompra> listarCarritoCompra() {
		return repo.findAll();
	}

	@Override
	public CarritoCompra agregarCarritoCompra(CarritoCompra nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public CarritoCompra buscarCarritoCompraById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<CarritoCompra> buscarPorLibroYUsuario(Libro libro, Usuario usuario) {
		return repo.findByObjLibroAndObjUsuario(libro, usuario);
	}

	@Override
	public String eliminarCarritoCompraById(int id) {
		repo.deleteById(id);
		return "Carrito de Compras Eliminado Exitosamente";
	}

	@Override
	public String eliminarCarritoCompraByUsuario(Usuario usuario) {
		repo.deleteByObjUsuario(usuario);
		return "Carrito de Compras Por Usuario Eliminado Exitosamente";
	}

	@Override
	public CarritoCompra actualizarCarritoCompra(CarritoCompra carritoAlquiler) {
		return repo.save(carritoAlquiler);
	}

	@Override
	public List<CarritoCompra> listarDatosCarrito(String usuarioID) {
		return repo.listarDatosCarrito(usuarioID);
	}

	@Override
	public Integer addToCart(String usuarioID, String libroID) {
		return repo.addToCart(usuarioID, libroID);
	}

	@Override
	public List<CarritoCompra> getBuyCarColumns() {
		return repo.getBuyCarColumns();
	}

}
