package common.GestionarNotificacion_Usuario;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.NotificacionDTO;
import common.DTOs.Notificacion_UsuarioDTO;
import common.DTOs.UsuarioDTO;

public interface IControlNotificacion_Usuario extends Remote{

	public Long agregarNotificacion_Usuario(Notificacion_UsuarioDTO notificacion_usuarioDTO)throws Exception;
	public void eliminarNotificacion_Usuario(Long id)throws Exception;
	public void modificarNotificacion_Usuario(Long id,Notificacion_UsuarioDTO modificado)throws Exception;
	
	public Vector<Notificacion_UsuarioDTO> obtenerNotificacion_Usuario()throws Exception;
	public Vector<Notificacion_UsuarioDTO> obtenerNotificacion_Usuario(UsuarioDTO usuarioDTO)throws Exception;

	public boolean existeNotificacion_Usuario(Long id) throws Exception;
	public boolean existeNotificacion_Usuario(NotificacionDTO notificacionDTO, UsuarioDTO usuarioDTO) throws Exception;
	
	public Notificacion_UsuarioDTO buscarNotificacion_Usuario(Long id) throws Exception;
	public Notificacion_UsuarioDTO buscarNotificacion_Usuario(NotificacionDTO notificacionDTO, UsuarioDTO usuarioDTO) throws Exception;

}
