package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import pe.com.toshokan.model.Editorial;

public interface IEditorialRepository extends JpaRepository<Editorial, String>{
	@Procedure(name = "getEditorialColumns")
	List<String> getEditorialColumns();
}
