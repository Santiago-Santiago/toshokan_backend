package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import pe.com.toshokan.model.CarritoCompra;
import pe.com.toshokan.model.Libro;
import pe.com.toshokan.model.Usuario;

public interface ICarritoDeCompraRepository extends JpaRepository<CarritoCompra, Integer> {
	@Procedure(name = "getBuyCarColumns")
	List<CarritoCompra> getBuyCarColumns();
	
	List<CarritoCompra> findByObjLibroAndObjUsuario(Libro objLibro, Usuario objUsuario);
	
	void deleteByObjUsuario(Usuario objUsuario);
	
	@Procedure(name = "listarDatosCarrito")
	List<CarritoCompra> listarDatosCarrito(@Param("usuario") String usuario);
	
	@Procedure(name="addToCart")
	Integer addToCart(@Param("user_id") String user_id, @Param("libCode") String libCode);
	
}
