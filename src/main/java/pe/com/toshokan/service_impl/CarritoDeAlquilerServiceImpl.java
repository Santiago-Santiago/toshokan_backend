package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.CarritoAlquiler;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.repository.ICarritoDeAlquilerRepository;
import pe.com.toshokan.service.CarritoDeAlquilerService;

@Service
public class CarritoDeAlquilerServiceImpl implements CarritoDeAlquilerService {

	@Autowired
	ICarritoDeAlquilerRepository repo;

	@Override
	public List<CarritoAlquiler> listarCarritoAlquiler() {
		return repo.findAll();
	}

	@Override
	public CarritoAlquiler agregarCarritoAlquiler(CarritoAlquiler nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public CarritoAlquiler buscarCarritoAlquilerById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<CarritoAlquiler> buscarPorLibroYUsuario(Libro libro, Usuario usuario) {
		return repo.findByObjLibroAndObjUsuario(libro, usuario);
	}

	@Override
	public String eliminarCarritoAlquilerById(int id) {
		repo.deleteById(id);
		return "Carrito de Alquiler Eliminado exitosamente";
	}

	@Override
	public String eliminarCarritoAlquilerByUsuario(Usuario usuario) {
		repo.deleteByObjUsuario(usuario);
		return "Carrito de Alquiler Por Usuario Eliminado exitosamente";
	}

	@Override
	public CarritoAlquiler actualizarCarritoAlquiler(CarritoAlquiler carritoAlquiler) {
		return repo.save(carritoAlquiler);
	}

	@Override
	public Integer addToCartAlquiler(String usuarioID, String libroID) {
		return repo.addToCartAlquiler(usuarioID, libroID);
	}

	@Override
	public List<CarritoAlquiler> listarDatosCarritoAlquiler(String usuarioID) {
		return repo.listarDatosCarritoAlquiler(usuarioID);
	}

	@Override
	public List<CarritoAlquiler> findLastCodeRentCar() {
		return repo.findLastCodeRentCar();
	}

	@Override
	public List<String> getRentcarColumns() {
		return repo.getRentcarColumns();
	}

}
