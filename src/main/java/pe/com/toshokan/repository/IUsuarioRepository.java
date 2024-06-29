package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import pe.com.toshokan.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
	List<Usuario> findByApellido(String apellido);

	@Procedure(name = "getUserColumns")
	List<String> getUserColumns();

	@Procedure(name = "validarIngresoUsuario")
	List<Usuario> validarIngresoUsuario(@Param("Usuario") String Usuario, @Param("pass") String pass);

}
