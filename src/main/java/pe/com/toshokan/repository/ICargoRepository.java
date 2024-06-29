package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import pe.com.toshokan.model.Cargo;

public interface ICargoRepository extends JpaRepository<Cargo, String>{
	@Procedure(name = "findLastCodeCargo")
	List<Cargo> findLastCodeCargo();

	@Procedure(name = "getCargoColumns")
	List<String> getCargoColumns();
}
