package pe.com.toshokan.service;

import java.util.List;
import pe.com.toshokan.model.BoletaCompra;
import pe.com.toshokan.model.Usuario;

public interface BoletaCompraService {
	public List<BoletaCompra> listarBoletaCompra();

	public BoletaCompra agregarBoletaCompra(BoletaCompra nuevo);

	public BoletaCompra buscarBoletaCompraById(int id);

	public List<BoletaCompra> buscarByUsuario(Usuario id);

	public String eliminarBoletaCompraById(int id);

	public BoletaCompra actualizarBoletaCompra(BoletaCompra boleta);
}
