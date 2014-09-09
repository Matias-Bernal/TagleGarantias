package common.GestionarUsuario;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.UsuarioDTO;

public interface IControlUsuario extends Remote{

	public Long agregarUsuario(UsuarioDTO usuario)throws Exception;
	public void eliminarUsuario(Long id)throws Exception;
	public void modificarUsuario(Long id,UsuarioDTO modificado)throws Exception;
	
	public Vector<UsuarioDTO> obtenerUsuarios()throws Exception;
	public Vector<UsuarioDTO> obtenerUsuarios(String tipo)throws Exception;

	public boolean login(String nombre_usuario, String password)throws Exception;
	
	public boolean existeUsuario(Long id) throws Exception;
	public boolean existeUsuario(String nombre_usuario) throws Exception;
	
	public UsuarioDTO buscarUsuario(Long id) throws Exception;
	public UsuarioDTO buscarUsuario(String nombre_usuario) throws Exception;
	
}