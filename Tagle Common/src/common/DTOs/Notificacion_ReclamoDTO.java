package common.DTOs;

public class Notificacion_ReclamoDTO extends ClaseGeneralDTO{

	private static final long serialVersionUID = 1L;
	private ReclamoDTO reclamo;
	private NotificacionDTO notificacion;
	
	public Notificacion_ReclamoDTO(ReclamoDTO reclamo,NotificacionDTO notificacion) {
		super();
		this.reclamo = reclamo;
		this.notificacion = notificacion;
	}

	public Notificacion_ReclamoDTO() {
	}

	public ReclamoDTO getReclamo() {
		return reclamo;
	}

	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
	}

	public NotificacionDTO getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(NotificacionDTO notificacion) {
		this.notificacion = notificacion;
	}
			
}
