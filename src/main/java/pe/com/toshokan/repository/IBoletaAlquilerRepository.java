package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.toshokan.model.BoletaAlquiler;
import pe.com.toshokan.model.Usuario;

public interface IBoletaAlquilerRepository extends JpaRepository<BoletaAlquiler, Integer> {
	List<BoletaAlquiler> findByObjUsuario(Usuario objUsuario);

}
