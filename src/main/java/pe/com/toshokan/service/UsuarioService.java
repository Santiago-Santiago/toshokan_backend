package pe.com.toshokan.service;

import java.util.List;

import pe.com.toshokan.model.Usuario;

public interface UsuarioService {
	public List<Usuario> listarUsuarios();

	public Usuario agregarUsuario(Usuario nuevo);

	public Usuario buscarUsuariosById(String id);

	public String eliminarUsuariosById(String id);

	public Usuario actualizarUsuarios(Usuario usuario);
	
	List<String> getUserColumns();

	List<Usuario> validarIngresoUsuario(String Username, String pass);
}
