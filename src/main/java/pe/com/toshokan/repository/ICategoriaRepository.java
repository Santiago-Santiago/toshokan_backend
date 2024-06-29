package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import pe.com.toshokan.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, String>{
	@Procedure(name = "getCategoriaColumns")
	List<String> getCategoriaColumns();
}
