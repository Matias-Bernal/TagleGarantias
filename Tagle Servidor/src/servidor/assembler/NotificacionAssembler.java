package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Notificacion;

import common.DTOs.NotificacionDTO;

public class NotificacionAssembler {
	
	private AccesoBD accesoBD;

	public NotificacionAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public NotificacionDTO getNotificacionDTO(Notificacion notificacion) {
		NotificacionDTO notificacionDTO = new NotificacionDTO();
		notificacionDTO.setId(notificacion.getId());
		notificacionDTO.setTexto_notificacion(notificacion.getTexto_notificacion());
		notificacionDTO.setFecha_notificacion(notificacion.getFecha_notificacion());
		notificacionDTO.setConcretada_notificacion(notificacion.getConcretada_notificacion());
		notificacionDTO.setTipo_notificacion(notificacion.getTipo_notificacion());
		return notificacionDTO;
	}

	public Notificacion getNotificacion(NotificacionDTO notificacionDTO) {
		Notificacion notificacion =  (Notificacion) accesoBD.buscarPorId(Notificacion.class, notificacionDTO.getId());
		notificacion.setId(notificacionDTO.getId());
		notificacion.setTexto_notificacion(notificacionDTO.getTexto_notificacion());
		notificacion.setFecha_notificacion(notificacionDTO.getFecha_notificacion());
		notificacion.setConcretada_notificacion(notificacionDTO.getConcretada_notificacion());
		notificacion.setTipo_notificacion(notificacionDTO.getTipo_notificacion());
		return notificacion;
	}

	public Notificacion getNotificacionNueva(NotificacionDTO notificacionDTO) {
		Notificacion notificacion =  new Notificacion();
		notificacion.setId(notificacionDTO.getId());
		notificacion.setTexto_notificacion(notificacionDTO.getTexto_notificacion());
		notificacion.setFecha_notificacion(notificacionDTO.getFecha_notificacion());
		notificacion.setConcretada_notificacion(notificacionDTO.getConcretada_notificacion());
		notificacion.setTipo_notificacion(notificacionDTO.getTipo_notificacion());
		return notificacion;
	}

}