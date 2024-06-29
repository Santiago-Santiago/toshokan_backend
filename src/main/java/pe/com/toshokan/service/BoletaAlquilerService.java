package pe.com.toshokan.service;

import java.util.List;
import pe.com.toshokan.model.BoletaAlquiler;
import pe.com.toshokan.model.Usuario;

public interface BoletaAlquilerService {

	public List<BoletaAlquiler> listarBoletaAlquiler();
	
	public BoletaAlquiler agregarBoletaAlquiler(BoletaAlquiler nuevo);
	
	public BoletaAlquiler buscarBoletaAlquilerById(int id);
	
	public List<BoletaAlquiler> buscarByUsuario(Usuario id);
	
	public String eliminarBoletaAlquilerById(int id);
	
	public BoletaAlquiler actualizarBoletaAlquiler(BoletaAlquiler boleta);
}
