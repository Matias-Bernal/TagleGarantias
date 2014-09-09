package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Notificacion_Reclamo;
import common.DTOs.EntidadDTO;
import common.DTOs.Notificacion_ReclamoDTO;

public class Notificacion_ReclamoAssembler {

	private AccesoBD accesoBD;
	
	public Notificacion_ReclamoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Notificacion_ReclamoDTO getNotificacion_ReclamoDTO(Notificacion_Reclamo notificacion_reclamo) {
		Notificacion_ReclamoDTO notificacion_reclamoDTO = new Notificacion_ReclamoDTO();
		notificacion_reclamoDTO.setId(notificacion_reclamo.getId());
		NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
		if(notificacion_reclamo.getNotificacion()!=null)
			notificacion_reclamoDTO.setNotificacion(notificacionAssemb.getNotificacionDTO(notificacion_reclamo.getNotificacion()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(notificacion_reclamo.getReclamo()!=null)
			notificacion_reclamoDTO.setReclamo(reclamoAssemb.getReclamoDTO(notificacion_reclamo.getReclamo()));
		return notificacion_reclamoDTO;
	}

	public Notificacion_Reclamo getNotificacion_Reclamo(Notificacion_ReclamoDTO notificacion_reclamoDTO) {
		Notificacion_Reclamo notificacion_reclamo = new Notificacion_Reclamo();
		notificacion_reclamo.setId(notificacion_reclamoDTO.getId());
		NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
		if(notificacion_reclamoDTO.getNotificacion()!=null)
			notificacion_reclamo.setNotificacion(notificacionAssemb.getNotificacion(notificacion_reclamoDTO.getNotificacion()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(notificacion_reclamoDTO.getReclamo()!=null)
			notificacion_reclamo.setReclamo(reclamoAssemb.getReclamo(notificacion_reclamoDTO.getReclamo()));
		return notificacion_reclamo;
	}

	public Notificacion_Reclamo getNotificacion_ReclamoNueva(Notificacion_ReclamoDTO notificacion_reclamoDTO) {
		Notificacion_Reclamo notificacion_reclamo = new Notificacion_Reclamo();
		notificacion_reclamo.setId(notificacion_reclamoDTO.getId());
		NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
		if(notificacion_reclamoDTO.getNotificacion()!=null)
			notificacion_reclamo.setNotificacion(notificacionAssemb.getNotificacion(notificacion_reclamoDTO.getNotificacion()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(notificacion_reclamoDTO.getReclamo()!=null)
			notificacion_reclamo.setReclamo(reclamoAssemb.getReclamo(notificacion_reclamoDTO.getReclamo()));
		return notificacion_reclamo;
	}
}
