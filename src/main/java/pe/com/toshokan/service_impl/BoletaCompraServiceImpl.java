package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.BoletaCompra;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.repository.IBoletaCompraRepository;
import pe.com.toshokan.service.BoletaCompraService;

@Service
public class BoletaCompraServiceImpl implements BoletaCompraService {
	
	@Autowired
	IBoletaCompraRepository repo;

	@Override
	public List<BoletaCompra> listarBoletaCompra() {
		return repo.findAll();
	}

	@Override
	public BoletaCompra agregarBoletaCompra(BoletaCompra nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public BoletaCompra buscarBoletaCompraById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<BoletaCompra> buscarByUsuario(Usuario id) {
		return repo.findByObjUsuario(id);
	}

	@Override
	public String eliminarBoletaCompraById(int id) {
		repo.deleteById(id);
		return "Boleta de Compra Eliminada Exitosamente";
	}

	@Override
	public BoletaCompra actualizarBoletaCompra(BoletaCompra boleta) {
		return repo.save(boleta);
	}

}
