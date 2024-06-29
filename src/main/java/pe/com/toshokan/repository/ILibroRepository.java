package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import pe.com.toshokan.model.Libro;

public interface ILibroRepository extends JpaRepository<Libro, String> {
	List<ILibroProjection> findBy();

	List<Libro> findByTituloContaining(String consulta);

	List<Libro> findByTitulo(String titulo);

	@Procedure(name = "descontarLibros")
	Integer descontarLibros(@Param("newStock") int newStock, @Param("codigo") String codigo);

	@Procedure(name = "getLibById")
	List<Libro> getLibById(@Param("id") String id);
}
