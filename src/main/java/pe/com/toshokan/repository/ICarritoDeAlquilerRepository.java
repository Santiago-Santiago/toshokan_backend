package pe.com.toshokan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import pe.com.toshokan.model.CarritoAlquiler;
import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.model.Libro;

public interface ICarritoDeAlquilerRepository extends JpaRepository<CarritoAlquiler, Integer> {
	void deleteByObjUsuario(Usuario objUsuario);

	List<CarritoAlquiler> findByObjLibroAndObjUsuario(Libro objLibro, Usuario objUsuario);

	@Procedure(name = "addToCartAlquiler")
	Integer addToCartAlquiler(String user_id, String libCode);

	@Procedure(name = "listarDatosCarritoAlquiler")
	List<CarritoAlquiler> listarDatosCarritoAlquiler(String usuario);

	@Procedure(name = "findLastCodeRentCar")
	List<CarritoAlquiler> findLastCodeRentCar();

	@Procedure(name = "getRentcarColumns")
	List<String> getRentcarColumns();
}
