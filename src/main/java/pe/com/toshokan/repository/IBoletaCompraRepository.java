package pe.com.toshokan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.toshokan.model.BoletaCompra;
import pe.com.toshokan.model.Usuario;

public interface IBoletaCompraRepository extends JpaRepository<BoletaCompra, Integer>{
List<BoletaCompra> findByObjUsuario(Usuario objUsuario);
}
