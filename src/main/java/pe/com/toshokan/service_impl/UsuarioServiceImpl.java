package pe.com.toshokan.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.toshokan.model.Usuario;
import pe.com.toshokan.repository.IUsuarioRepository;
import pe.com.toshokan.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	IUsuarioRepository repo;
	
	@Override
	public List<Usuario> listarUsuarios() {		
		return repo.findAll();
	}

	@Override
	public Usuario agregarUsuario(Usuario nuevo) {		
		return repo.save(nuevo);
	}

	@Override
	public Usuario buscarUsuariosById(String id) {		
		return repo.findById(id).get();
	}

	@Override
	public String eliminarUsuariosById(String id) {		
		repo.deleteById(id);
		return "Usuario Eliminado con Exito";
	}

	@Override
	public Usuario actualizarUsuarios(Usuario usuario) {		
		return repo.save(usuario);
	}

	@Override
	public List<String> getUserColumns() {		
		return repo.getUserColumns();
	}

	@Override
	public List<Usuario> validarIngresoUsuario(String Username, String pass) {		
		return repo.validarIngresoUsuario(Username, pass);
	}

}
