package common.GestionarNotificacion_Reclamo;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.NotificacionDTO;
import common.DTOs.Notificacion_ReclamoDTO;
import common.DTOs.ReclamoDTO;

public interface IControlNotificacion_Reclamo extends Remote{
	
	public Long agregarNotificacionReclamo(Notificacion_ReclamoDTO notificacion_reclamoDTO)throws Exception;
	public void eliminarNotificacionReclamo(Long id)throws Exception;
	public void modificarNotificacionReclamo(Long id,Notificacion_ReclamoDTO modificado)throws Exception;
	
	public Vector<Notificacion_ReclamoDTO> obtenerNotificaciones_Reclamos()throws Exception;
	public Vector<Notificacion_ReclamoDTO> obtenerNotificaciones_Reclamo(ReclamoDTO reclamo)throws Exception;
	public Vector<Notificacion_ReclamoDTO> obtenerNotificaciones_Reclamo(NotificacionDTO notificacion)throws Exception;
	
	public boolean existeNotificacionReclamo(Long id) throws Exception;
	public boolean existeNotificacionReclamo(ReclamoDTO reclamo,NotificacionDTO notificacion) throws Exception;
	
	public Notificacion_ReclamoDTO buscarNotificacionReclamo(Long id) throws Exception;
	public Notificacion_ReclamoDTO buscarNotificacionReclamo(ReclamoDTO reclamo,NotificacionDTO notificacion) throws Exception;

}