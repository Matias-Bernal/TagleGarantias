package common.GestionarNotificacion;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.NotificacionDTO;

public interface IControlNotificacion extends Remote{
	
	public Long agregarNotificacion(NotificacionDTO notificacionDTO)throws Exception;
	public void eliminarNotificacion(Long id)throws Exception;
	public void modificarNotificacion(Long id,NotificacionDTO modificado)throws Exception;
	
	public Vector<NotificacionDTO> obtenerNotificacion()throws Exception;
	public Vector<NotificacionDTO> obtenerNotificacion(Boolean concretada_notificacion)throws Exception;
	public Vector<NotificacionDTO> obtenerNotificacion(String tipo_notificacion)throws Exception;
	
	public boolean existeNotificacion(Long id) throws Exception;
	public boolean existeNotificacion(NotificacionDTO notificacionDTO) throws Exception;
	
	public NotificacionDTO buscarNotificacion(Long id) throws Exception;
	public NotificacionDTO buscarNotificacion(NotificacionDTO notificacionDTO) throws Exception;


}
