package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import pe.com.toshokan.model.Autor;

public interface IAutorRepository extends JpaRepository<Autor, String>{
	@Procedure(name = "findLastCodeAutor")
	List<Autor> findLastCodeAutor();

	@Procedure(name = "getAutorColumns")
	List<String> getAutorColumns();
}
