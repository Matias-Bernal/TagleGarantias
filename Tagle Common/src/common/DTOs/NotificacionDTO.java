package common.DTOs;

import java.sql.Date;

public class NotificacionDTO extends ClaseGeneralDTO{

	protected static final long serialVersionUID = 1L;
	protected Date fecha_notificacion;
	protected Boolean concretada_notificacion;
	protected String texto_notificacion;
	protected String tipo_notificacion;
	
	public NotificacionDTO() {}
	public NotificacionDTO(Date fecha_notificacion, Boolean concretada_notificacion, String texto_notificacion,String tipo_notificacion) {
		super();
		this.fecha_notificacion = fecha_notificacion;
		this.concretada_notificacion = concretada_notificacion;
		this.texto_notificacion = texto_notificacion;
		this.tipo_notificacion = tipo_notificacion;
	}

	public String getTipo_notificacion() {
		return tipo_notificacion;
	}

	public void setTipo_notificacion(String tipo_notificacion) {
		this.tipo_notificacion = tipo_notificacion;
	}

	public Date getFecha_notificacion() {
		return fecha_notificacion;
	}
	public void setFecha_notificacion(Date fecha_notificacion) {
		this.fecha_notificacion = fecha_notificacion;
	}
	public Boolean getConcretada_notificacion() {
		return concretada_notificacion;
	}
	public void setConcretada_notificacion(Boolean concretada_notificacion) {
		this.concretada_notificacion = concretada_notificacion;
	}
	public String getTexto_notificacion() {
		return texto_notificacion;
	}
	public void setTexto_notificacion(String texto_notificacion) {
		this.texto_notificacion = texto_notificacion;
	}
	
}
