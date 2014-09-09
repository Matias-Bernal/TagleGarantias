package common.DTOs;

public class Notificacion_UsuarioDTO extends ClaseGeneralDTO {

	private static final long serialVersionUID = 1L;
	private NotificacionDTO notificacion;
	private UsuarioDTO usuario;
	
	public Notificacion_UsuarioDTO(){}
	public Notificacion_UsuarioDTO(NotificacionDTO notificacion,
			UsuarioDTO usuario) {
		super();
		this.notificacion = notificacion;
		this.usuario = usuario;
	}
	public NotificacionDTO getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(NotificacionDTO notificacion) {
		this.notificacion = notificacion;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}
