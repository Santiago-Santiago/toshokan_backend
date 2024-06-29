package pe.com.toshokan.service_impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.toshokan.model.BoletaAlquiler;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.repository.IBoletaAlquilerRepository;
import pe.com.toshokan.service.BoletaAlquilerService;

@Service
public class BoletaAlquilerServiceImpl implements BoletaAlquilerService {

	@Autowired
	IBoletaAlquilerRepository repo;

	@Override
	public List<BoletaAlquiler> listarBoletaAlquiler() {
		return repo.findAll();
	}

	@Override
	public BoletaAlquiler agregarBoletaAlquiler(BoletaAlquiler nuevo) {
		return repo.save(nuevo);
	}

	@Override
	public BoletaAlquiler actualizarBoletaAlquiler(BoletaAlquiler boleta) {
		return repo.save(boleta);
	}

	@Override
	public BoletaAlquiler buscarBoletaAlquilerById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<BoletaAlquiler> buscarByUsuario(Usuario id) {
		return repo.findByObjUsuario(id);
	}

	@Override
	public String eliminarBoletaAlquilerById(int id) {
		repo.deleteById(id);
		return "Boleta De Alquiler Eliminada con exito";
	}

}
